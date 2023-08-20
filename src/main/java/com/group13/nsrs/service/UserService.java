package com.group13.nsrs.service;

import com.group13.nsrs.model.dto.LoginDto;
import com.group13.nsrs.model.dto.RegisterDto;
import com.group13.nsrs.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group13.nsrs.util.Result;

/**
 * @author Oreki
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-08-20 11:38:25
 */
public interface UserService extends IService<User> {

    Result<String> login(LoginDto loginDto);

    Result<String> register(RegisterDto registerDto);

    boolean sendCode(String phone, String code);
}
