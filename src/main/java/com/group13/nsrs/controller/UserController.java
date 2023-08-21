package com.group13.nsrs.controller;

import cn.hutool.core.bean.BeanUtil;
import com.group13.nsrs.model.dto.LoginDto;
import com.group13.nsrs.model.dto.RegisterDto;
import com.group13.nsrs.model.dto.UpdatePWDto;
import com.group13.nsrs.model.dto.UserUpdateDto;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.model.vo.LoginVo;
import com.group13.nsrs.model.vo.UserVo;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.util.RandomUtil;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Fu Qiujie
 * @since 2023/8/20
 */

@RestController
@CrossOrigin //用于处理用户跨域问题
@RequestMapping("/api/user")
@Api(tags = "用户信息接口")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录成功后会返回用户基本信息和token，若是初次登录会返回错误码250，需要前端跳转到注册页面")
    public Result<LoginVo> login(@RequestBody
                                 @ApiParam(value = "登录信息", required = true)
                                 LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "注册成功后会返回用户基本信息和token")
    public Result<LoginVo> register(@RequestBody
                                    @ApiParam(value = "注册信息", required = true)
                                    RegisterDto registerDto) {
        return userService.register(registerDto);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "获取指定用户信息")
    public Result<UserVo> getUserInfo(@PathVariable
                                      @ApiParam(value = "用户id", required = true)
                                      Long id) {
        return userService.getUserInfo(id);
    }

    @PutMapping
    @ApiOperation(value = "修改用户信息")
    public Result<String> updateUser(@RequestBody
                                     @ApiParam(value = "待更新用户信息", required = true)
                                     UserUpdateDto userUpdateDto) {
        return userService.updateUser(userUpdateDto);
    }

    @ApiOperation("更改密码")
    @PutMapping("/password")
    public Result<String> updatePassword(@RequestBody
                                         @ApiParam(value = "密码更新信息", required = true)
                                         UpdatePWDto updatePWDto) {
        return userService.updatePassword(updatePWDto);
    }


    @ApiOperation("发送短信验证码")
    @GetMapping("/send/{phone}")
    public Result<String> sendCode(@PathVariable
                                   @ApiParam(value = "手机号", required = true)
                                   String phone) {
        return userService.sendCode(phone);
    }

    @ApiOperation("找回密码")
    @GetMapping("password/{phone}/{code}/")
    public Result<UserVo> findPassword(@PathVariable
                                       @ApiParam(value = "手机号", required = true)
                                       String phone,
                                       @PathVariable
                                       @ApiParam(value = "验证码", required = true)
                                       String code) {
        return userService.findPassword(phone, code);
    }
}
