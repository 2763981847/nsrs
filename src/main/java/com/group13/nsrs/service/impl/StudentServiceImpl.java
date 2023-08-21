package com.group13.nsrs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.dto.StudentUpdateDto;
import com.group13.nsrs.model.entity.Student;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.service.StudentService;
import com.group13.nsrs.mapper.StudentMapper;
import com.group13.nsrs.service.UserService;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import com.group13.nsrs.util.thread.ThreadLocalUtil;
import com.sun.corba.se.spi.ior.iiop.IIOPFactories;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Oreki
 * @description 针对表【student】的数据库操作Service实现
 * @createDate 2023-08-20 11:00:40
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

    @Resource
    private UserService userService;

    @Override
    public Result<String> updateStudent(StudentUpdateDto studentUpdateDto) {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String snumber = userService.getById(user.getId()).getSnumber();
        Student student = BeanUtil.copyProperties(studentUpdateDto, Student.class);
        boolean success = this.lambdaUpdate().eq(Student::getSnumber, snumber).update(student);
        return Result.judge(success);
    }

    @Override
    public Result<Student> getStudentInfo() {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String snumber = userService.getById(user.getId()).getSnumber();
        Student student = this.lambdaQuery().eq(Student::getSnumber, snumber).one();
        return Result.ok(student);
    }
}




