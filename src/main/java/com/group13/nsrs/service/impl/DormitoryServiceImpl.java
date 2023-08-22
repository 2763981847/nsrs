package com.group13.nsrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.entity.Dormitory;
import com.group13.nsrs.model.entity.Student;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.service.DormitoryService;
import com.group13.nsrs.mapper.DormitoryMapper;
import com.group13.nsrs.service.StudentService;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import com.group13.nsrs.util.thread.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Oreki
 * @description 针对表【dormitory】的数据库操作Service实现
 * @createDate 2023-08-22 15:51:51
 */
@Service
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory>
        implements DormitoryService {
    @Resource
    private StudentService studentService;

    @Override
    public Result<List<Dormitory>> listDormitoriesByUser() {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String snumber = user.getSnumber();
        Student student = studentService
                .lambdaQuery()
                .eq(Student::getSnumber, snumber).one();
        return Result.ok();
    }
}




