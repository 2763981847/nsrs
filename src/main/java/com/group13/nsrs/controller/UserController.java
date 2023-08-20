package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.LoginVo;
import com.group13.nsrs.model.dto.UserUpdateDto;
import com.group13.nsrs.model.vo.UserInfoVo;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public Result<String> login(LoginVo loginVo) {
        return userService.login(loginVo);
    }

    @GetMapping("/{id}")
    public Result<UserInfoVo> getUserInfo(@PathVariable Long id) {
        return Result.ok();
    }

    @PutMapping
    public Result<String> updateUser(UserUpdateDto userUpdateDto) {
        return Result.ok();
    }

    @PutMapping("/password/{oldPassword}/{newPassword}")
    public Result<String> updatePassword(@PathVariable String oldPassword, @PathVariable String newPassword) {
        return Result.ok();
    }

}
