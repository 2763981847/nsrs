package com.group13.nsrs.service;

import com.group13.nsrs.NsrsApplication;
import com.group13.nsrs.model.entity.Major;
import com.group13.nsrs.model.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * @author Fu Qiujie
 * @since 2023/8/21
 */
@SpringBootTest(classes = NsrsApplication.class)
@RunWith(SpringRunner.class)
public class StudentInitTest {
    @Resource
    private StudentService studentService;

    @Resource
    private MajorService majorService;


    @Test
    public void setStudentCollegeByMajor() {
        List<Student> students = studentService.list();
        students.forEach(student -> {
            Long majorId = student.getMajorId();
            Major major = majorService.getById(majorId);
            if (major == null) {
                studentService.removeById(student.getId());
                return;
            }
            Long collegeId = major.getCollegeId();
            student.setCollegeId(collegeId);
            studentService.updateById(student);
        });
    }
}