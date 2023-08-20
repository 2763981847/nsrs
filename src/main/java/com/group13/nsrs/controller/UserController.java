package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.LoginDto;
import com.group13.nsrs.model.dto.RegisterDto;
import com.group13.nsrs.model.dto.UserUpdateDto;
import com.group13.nsrs.model.vo.UserInfoVo;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.util.RandomUtil;
import com.group13.nsrs.util.Result;
import com.group13.nsrs.util.ResultCodeEnum;
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
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }


    @GetMapping("/{id}")
    public Result<UserInfoVo> getUserInfo(@PathVariable Long id) {
        return Result.ok();
    }

    @PutMapping
    public Result<String> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        return Result.ok();
    }

    @PutMapping("/password/{oldPassword}/{newPassword}")
    public Result<String> updatePassword(@PathVariable String oldPassword, @PathVariable String newPassword) {
        return Result.ok();
    }

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation("发送短信验证码")
    @GetMapping("/send/{phone}")
    public Result<String> sendCode(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        //如果Redis中已经存在验证码则直接返回
        if (code != null) {
            return Result.ok(code);
        }
        //通过工具类生成一个六位验证码
        code = RandomUtil.getSixBitRandom();
        //调用短信发送服务
        boolean isSend = userService.sendCode(phone, code);
        if (isSend) {
            //发送成功则将验证码存入Redis，并设置5分钟有效时间
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return Result.ok(code);
        } else {
            return Result.fail(ResultCodeEnum.SERVICE_ERROR, "短信发送失败");
        }
    }
}
