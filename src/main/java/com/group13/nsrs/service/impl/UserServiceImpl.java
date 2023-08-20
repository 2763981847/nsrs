package com.group13.nsrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.dto.LoginDto;
import com.group13.nsrs.model.dto.RegisterDto;
import com.group13.nsrs.model.entity.Student;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.service.StudentService;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.mapper.UserMapper;
import com.group13.nsrs.util.JwtHelper;
import com.group13.nsrs.util.Result;
import com.group13.nsrs.util.ResultCodeEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oreki
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-08-20 11:38:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private StudentService studentService;

    @Override
    public Result<String> login(LoginDto loginDto) {
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
            if ("123456".equals(password)) {
                // 初次登录成功，需要绑定手机号
                return Result.fail(ResultCodeEnum.FIRST_LOGIN);
            } else {
                return Result.fail(ResultCodeEnum.PASSWORD_ERROR);
            }
        }
        // 非初次登录，校验密码是否正确
        String salt = user.getSalt();
        String encryptPassword = user.getPassword();
        if (!encryptPassword.equals(DigestUtils.md5DigestAsHex((password + salt).getBytes()))) {
            // 密码错误
            return Result.fail(ResultCodeEnum.PASSWORD_ERROR);
        }
        // 登录成功返回token
        String token = JwtHelper.createToken(user.getId());
        return Result.ok(token);
    }

    @Override
    public Result<String> register(RegisterDto registerDto) {
        return null;
    }

    @Override
    public boolean sendCode(String phone, String code) {
        //手机号不正确则返回
        if (!UserConstant.CHINESE_PHONE_PATTERN.matcher(phone).matches()) {
            return false;
        }
        //整合阿里云短信服务
        //设置相关参数
        DefaultProfile profile = DefaultProfile.
                getProfile(ConstantPropertiesUtil.REGION_Id,
                        ConstantPropertiesUtil.ACCESS_KEY_ID,
                        ConstantPropertiesUtil.SECRET);
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
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
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
}




