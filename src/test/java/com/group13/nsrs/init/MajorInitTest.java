package com.group13.nsrs.init;

import com.group13.nsrs.NsrsApplication;
import com.group13.nsrs.model.entity.College;
import com.group13.nsrs.service.CollegeService;
import com.group13.nsrs.service.MajorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@SpringBootTest(classes = NsrsApplication.class)
@RunWith(SpringRunner.class)
public class MajorInitTest {
    @Resource
    private MajorService majorService;

    @Resource
    private CollegeService collegeService;

    @Test
    public void fillCollegeNameByCollegeId() {
        majorService.list().forEach(major -> {
            Long collegeId = major.getCollegeId();
            College college = collegeService.getById(collegeId);
            if (college == null) {
                majorService.removeById(major.getId());
                return;
            }
            major.setCollegeName(college.getName());
            majorService.updateById(major);
        });
    }
}
