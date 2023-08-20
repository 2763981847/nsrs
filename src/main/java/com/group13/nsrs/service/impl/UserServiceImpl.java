package com.group13.nsrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.entity.User;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Oreki
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-08-20 10:49:28
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




