package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.*;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/statistic")
@Api(tags = "可视化大屏接口")
public class HtmlController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @ApiOperation(value = "统计报道成功的学生人数",
            protocols = "http",
            httpMethod="GET",
            response= Result.class,
            notes = "统计报道成功的学生人数")
    @GetMapping("/dynamicNum")

    public Result dynamicNnum(){
        try {
            String sql = "SELECT\n" +
                    "    COUNT(*) AS dynamic_num\n" +
                    "FROM\n" +
                    "    student\n" +
                    "WHERE\n" +
                    "    reported_status = 7;";
            List<DynamicNumDTO> dynNum= jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(DynamicNumDTO.class));
            return Result.ok(dynNum);
        }catch (Exception e){
            return  Result.fail(ResultCodeEnum.DATA_ERROR);
        }
    }

    //统计每天报道的人数
    @ApiOperation(value = "按照日期统计人数",
            protocols = "http",
            httpMethod="GET",
            response= Result.class,
            notes = "特定日期报道的人数")
    @GetMapping("/dayReportNum")

    public Result dayReportNum(){
        try {
            String sql = "SELECT report_date, COUNT(*) AS count\n" +
                    "FROM student\n" +
                    "GROUP BY report_date;";
            List<DayReportDTo> dayReort= jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(DayReportDTo.class));
            return Result.ok(dayReort);
        }catch (Exception e){
            return  Result.fail(ResultCodeEnum.DATA_ERROR);
        }
    }
    //统计男生和女生总人数
    @ApiOperation(value = "统计男生和女生总人数",
            protocols = "http",
            httpMethod="GET",
            response= Result.class,
            notes = "统计男生和女生总人数")
    @GetMapping("/genderRatio")

    public Result genderRation(){
        try {
            String sql = "SELECT\n" +
                    "    SUM(\n" +
                    "        CASE WHEN sex = 0 THEN 1 ELSE 0\n" +
                    "    END\n" +
                    ") AS male_count,\n" +
                    "SUM(\n" +
                    "    CASE WHEN sex = 1 THEN 1 ELSE 0\n" +
                    "END\n" +
                    ") AS female_count\n" +
                    "FROM\n" +
                    "    student;";
            List<GenderRatioDTO> genRa= jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(GenderRatioDTO.class));
            return Result.ok(genRa);
        }catch (Exception e){
            return  Result.fail(ResultCodeEnum.DATA_ERROR);
        }
    }


    @ApiOperation(value = "统计年龄分布",
            protocols = "http",
            httpMethod="GET",
            response= Result.class,
            notes = "统计年龄分布")
    @GetMapping("/ageDistrition")

    public Result ageDistri(){
        try {
            //根据所填出生日期计算年龄
            String sql = "SELECT \n" +
                    "    TIMESTAMPDIFF(YEAR, birth, CURDATE()) AS age,\n" +
                    "    COUNT(*) AS age_count\n" +
                    "FROM\n" +
                    "    student\n" +
                    "GROUP BY\n" +
                    "    age\n" +
                    "ORDER BY\n" +
                    "    age;\n";
            List<AgeDistributionDTO> ageDis= jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(AgeDistributionDTO.class));
            return Result.ok(ageDis);
        }catch (Exception e){
            return  Result.fail(ResultCodeEnum.DATA_ERROR);
        }
    }


    @ApiOperation(value = "统计兴趣爱好分布",
            protocols = "http",
            httpMethod="GET",
            response= Result.class,
            notes = "统计兴趣爱好分布")
    @GetMapping("/hobbiesDistri")

    public Result hobbiesDistri(){
        try {
            List<String> hobbies = jdbcTemplate.queryForList("SELECT hobbies FROM user", String.class);
            List<String> sp_hobbies = new ArrayList<>();

            for (String hobby : hobbies) {
                String[] splittedHobbies = hobby.split(",");
                sp_hobbies.addAll(Arrays.asList(splittedHobbies));
            }

            Map<String, Integer> map = new HashMap<>();
            sp_hobbies.forEach(hobby -> map.put(hobby, map.getOrDefault(hobby, 0) + 1));

//            System.out.println(map);
            return Result.ok(map);

        }catch (Exception e){
            return  Result.fail(ResultCodeEnum.DATA_ERROR);
        }
    }


}
