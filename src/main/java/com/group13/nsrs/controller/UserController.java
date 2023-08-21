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
import io.swagger.annotations.ApiOperation;
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
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/register")
    public Result<LoginVo> register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }


    @GetMapping("/{id}")
    public Result<UserVo> getUserInfo(@PathVariable Long id) {
        return userService.getUserInfo(id);
    }

    @PutMapping
    public Result<String> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(userUpdateDto);
    }

    @ApiOperation("更改密码")
    @PutMapping("/password")
    public Result<String> updatePassword(@RequestBody UpdatePWDto updatePWDto) {
        return userService.updatePassword(updatePWDto);
    }


    @ApiOperation("发送短信验证码")
    @GetMapping("/send/{phone}")
    public Result<String> sendCode(@PathVariable String phone) {
        return userService.sendCode(phone);
    }

    @ApiOperation("找回密码")
    @GetMapping("password/{phone}/{code}/")
    public Result<UserVo> findPassword(@PathVariable String phone, @PathVariable String code) {
        return userService.findPassword(phone, code);
    }
}
