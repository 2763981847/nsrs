package com.group13.nsrs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.constant.UserConstants;
import com.group13.nsrs.model.dto.LoginDto;
import com.group13.nsrs.model.dto.RegisterDto;
import com.group13.nsrs.model.dto.UpdatePWDto;
import com.group13.nsrs.model.dto.UserUpdateDto;
import com.group13.nsrs.model.entity.Student;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.model.vo.LoginVo;
import com.group13.nsrs.model.vo.UserVo;
import com.group13.nsrs.service.StudentService;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.mapper.UserMapper;
import com.group13.nsrs.util.*;
import com.group13.nsrs.util.redis.CacheService;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import com.group13.nsrs.util.thread.ThreadLocalUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Oreki
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-08-20 11:38:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private StudentService studentService;

    @Resource
    private CacheService cacheService;

    @Override
    public Result<LoginVo> login(LoginDto loginDto) {
        String snumber = loginDto.getSnumber();
        String password = loginDto.getPassword();
        if (StringUtils.isEmpty(snumber)) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR, "学号不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR, "密码不能为空");
        }
        Long count = studentService.lambdaQuery().eq(Student::getSnumber, snumber).count();
        if (count == 0) {
            return Result.fail(ResultCodeEnum.DATA_NOT_EXIST, "学号不存在");
        }
        User user = this.lambdaQuery().eq(User::getSnumber, snumber).one();
        if (user == null) {
            // 初次登录，校验密码是否为默认密码
            if (UserConstants.DEFAULT_PASSWORD.equals(password)) {
                // 初次登录成功，需要绑定手机号
                return Result.fail(ResultCodeEnum.FIRST_LOGIN);
            } else {
                return Result.fail(ResultCodeEnum.PASSWORD_ERROR);
            }
        }
        // 非初次登录，校验密码是否正确
        String salt = user.getSalt();
        String encryptPassword = user.getPassword();
        if (!encryptPassword.equals(encryptPassword(password, salt))) {
            // 密码错误
            return Result.fail(ResultCodeEnum.PASSWORD_ERROR);
        }
        // 登录成功返回token和用户信息
        String token = JwtHelper.createToken(user);
        LoginVo loginVo = BeanUtil.copyProperties(user, LoginVo.class);
        loginVo.setToken(token);
        return Result.ok(loginVo);
    }

    @NotNull
    private static String encryptPassword(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }

    @Override
    public Result<LoginVo> register(RegisterDto registerDto) {
        String snumber = registerDto.getSnumber();
        if (StringUtils.isEmpty(snumber)) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR, "学号不能为空");
        }

        //  检验验证码是否一致
        String phone = registerDto.getPhone();
        String code = registerDto.getCode();
        boolean success = checkCode(phone, code);
        if (!success) return Result.fail(ResultCodeEnum.CODE_ERROR);
        // 新建用户
        User user = BeanUtil.copyProperties(registerDto, User.class);
        user.setSalt(RandomUtil.getFourBitRandom());
        user.setPassword(encryptPassword(user.getPassword(), user.getSalt()));
        this.save(user);
        String token = JwtHelper.createToken(user);
        LoginVo loginVo = BeanUtil.copyProperties(user, LoginVo.class);
        loginVo.setToken(token);
        return Result.ok(loginVo);
    }

    private boolean checkCode(String phone, String code) {
        String key = UserConstants.USER_CODE_KEY + phone;
        String realCode = cacheService.get(key);
        if (!code.equals(realCode)) {
            return false;
        }
        // 验证码一致，删除redis中的验证码
        cacheService.delete(key);
        return true;
    }

    @Override
    public boolean sendCode(String phone, String code) {
        //手机号不正确则返回
        if (!Validator.isMobile(phone)) {
            return false;
        }
        //整合阿里云短信服务
        //设置相关参数
        DefaultProfile profile = DefaultProfile.getProfile(ConstantPropertiesUtil.REGION_Id, ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //签名名称
        request.putQueryParameter("SignName", "阿里云短信测试");
        //模板code
        request.putQueryParameter("TemplateCode", "SMS_154950909");
        //验证码  使用json格式   {"code":"123456"}
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(param));
        //调用方法进行短信发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Result<UserVo> findPassword(String phone, String code) {
        boolean success = checkCode(phone, code);
        if (!success) return Result.fail(ResultCodeEnum.CODE_ERROR);
        User user = this.lambdaQuery().eq(User::getPhone, phone).one();
        if (user == null) {
            return Result.fail(ResultCodeEnum.DATA_NOT_EXIST, "用户不存在");
        }
        return Result.ok(BeanUtil.copyProperties(user, UserVo.class));
    }

    @Override
    public Result<String> updatePassword(UpdatePWDto updatePWDto) {
        // 判断是修改密码还是重置密码
        String oldPassword = updatePWDto.getOldPassword();
        Long userId = updatePWDto.getUserId();
        String newPassword = updatePWDto.getNewPassword();
        User user = this.getById(userId);
        if (StringUtils.isEmpty(oldPassword)) {
            // 重置密码
            boolean success = this.updatePassword(user, newPassword);
            return Result.judge(success);
        }
        // 修改密码
        // 校验旧密码是否正确
        String salt = user.getSalt();
        String encryptPassword = encryptPassword(oldPassword, salt);
        if (!encryptPassword.equals(user.getPassword())) {
            return Result.fail(ResultCodeEnum.PASSWORD_ERROR);
        }
        // 校验新密码是否与旧密码相同
        if (oldPassword.equals(newPassword)) {
            return Result.fail(ResultCodeEnum.PASSWORD_ERROR, "新旧密码不能相同");
        }
        // 修改密码
        return Result.judge(this.updatePassword(user, newPassword));
    }

    @Override
    public Result<UserVo> getUserInfo(Long id) {
        User user = this.getById(id);
        if (user == null) {
            return Result.fail(ResultCodeEnum.DATA_NOT_EXIST, "用户不存在");
        }
        return Result.ok(BeanUtil.copyProperties(user, UserVo.class));
    }

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public Result<String> updateUser(UserUpdateDto userUpdateDto) {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        BeanUtil.copyProperties(userUpdateDto, user);
        boolean success = this.updateById(user);
        return Result.judge(success);
    }

    @Override
    public Result<String> sendCode(String phone) {
        String key = UserConstants.USER_CODE_KEY + phone;
        String code = cacheService.get(key);
        //如果Redis中已经存在验证码则直接返回
        if (code != null) {
            return Result.ok(code);
        }
        //通过工具类生成一个六位验证码
        code = RandomUtil.getSixBitRandom();
        //调用短信发送服务
//        boolean isSend = this.sendCode(phone, code);
        boolean isSend = true;
        if (isSend) {
            //发送成功则将验证码存入Redis，并设置5分钟有效时间
            cacheService.setEx(key, code, 5, TimeUnit.MINUTES);
            // 为了方便测试，直接返回验证码
            return Result.ok(code);
        } else {
            return Result.fail(ResultCodeEnum.SERVICE_ERROR, "短信发送失败");
        }
    }

    private boolean updatePassword(User user, String newPassword) {
        if (user == null) {
            return false;
        }
        String salt = user.getSalt();
        String encryptPassword = encryptPassword(newPassword, salt);
        user.setPassword(encryptPassword);
        return this.updateById(user);
    }

}




