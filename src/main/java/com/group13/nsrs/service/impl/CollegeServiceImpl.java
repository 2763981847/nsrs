package com.group13.nsrs.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.entity.College;
import com.group13.nsrs.model.entity.Major;
import com.group13.nsrs.model.vo.MultiLevelSelection;
import com.group13.nsrs.service.CollegeService;
import com.group13.nsrs.mapper.CollegeMapper;
import com.group13.nsrs.service.MajorService;
import com.group13.nsrs.util.result.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oreki
 * @description 针对表【college】的数据库操作Service实现
 * @createDate 2023-08-20 11:00:40
 */
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College>
        implements CollegeService {
    @Resource
    private MajorService majorService;

    @Override
    public Result<List<MultiLevelSelection>> listCollegesWithMajors() {
        List<College> colleges = this.list();
        List<MultiLevelSelection> multiLevelSelections = colleges.parallelStream().map(
                college -> {
                    MultiLevelSelection multiLevelSelection = new MultiLevelSelection();
                    multiLevelSelection.setValue(college.getId());
                    multiLevelSelection.setLabel(college.getName());
                    multiLevelSelection.setChildren(majorService.lambdaQuery().eq(Major::getCollegeId, college.getId()).list().stream().map(
                            major -> {
                                MultiLevelSelection majorMultiLevelSelection = new MultiLevelSelection();
                                majorMultiLevelSelection.setValue(major.getId());
                                majorMultiLevelSelection.setLabel(major.getName());
                                return majorMultiLevelSelection;
                            }
                    ).collect(Collectors.toList()));
                    return multiLevelSelection;
                }
        ).collect(Collectors.toList());
        return Result.ok(multiLevelSelections);
    }

}




