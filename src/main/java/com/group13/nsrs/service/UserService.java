package com.group13.nsrs.service;

import com.group13.nsrs.model.dto.LoginDto;
import com.group13.nsrs.model.dto.RegisterDto;
import com.group13.nsrs.model.dto.UpdatePWDto;
import com.group13.nsrs.model.dto.UserUpdateDto;
import com.group13.nsrs.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group13.nsrs.model.vo.LoginVo;
import com.group13.nsrs.model.vo.UserVo;
import com.group13.nsrs.util.result.Result;

/**
 * @author Oreki
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-08-20 11:38:25
 */
public interface UserService extends IService<User> {

    Result<LoginVo> login(LoginDto loginDto);

    Result<LoginVo> register(RegisterDto registerDto);

    boolean sendCode(String phone, String code);

    Result<UserVo> findPassword(String phone, String code);

    Result<String> updatePassword(UpdatePWDto updatePWDto);

    Result<UserVo> getUserInfo(Long id);

    Result<String> updateUser(UserUpdateDto userUpdateDto);

    Result<String> sendCode(String phone);
}
