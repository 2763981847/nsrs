package com.group13.nsrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.dto.LoginVo;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.mapper.UserMapper;
import com.group13.nsrs.util.Result;
import com.group13.nsrs.util.ResultCodeEnum;
import org.springframework.stereotype.Service;

/**
 * @author Oreki
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-08-20 11:38:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public Result<String> login(LoginVo loginVo) {
        String snumber = loginVo.getSnumber();
        String password = loginVo.getPassword();
        if (snumber == null || password == null) {
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }
}




