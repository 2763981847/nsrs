package com.group13.nsrs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.entity.Major;
import com.group13.nsrs.service.MajorService;
import com.group13.nsrs.mapper.MajorMapper;
import com.group13.nsrs.util.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oreki
 * @description 针对表【major】的数据库操作Service实现
 * @createDate 2023-08-20 11:00:40
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major>
        implements MajorService {

    @Override
    public Result<List<Major>> listMajorsByCollegeId(Long collegeId) {
        List<Major> majors = this.lambdaQuery().eq(Major::getCollegeId, collegeId).list();
        return Result.ok(majors);
    }
}




