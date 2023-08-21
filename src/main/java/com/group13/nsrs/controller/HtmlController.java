package com.group13.nsrs.controller;

import com.group13.nsrs.model.dto.genderRatioDTO;
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

import java.util.List;
@RestController
@RequestMapping("/api/statistic")
@Api(tags = "可视化大屏接口")
public class HtmlController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @ApiOperation(value = "统计各个学院男女生总人数",
            protocols = "http",
            httpMethod="GET",
            response= Result.class,
            notes = "统计各个学院男女生总人数")
    @GetMapping("/genderRatio")

    public Result queryAll(){
        try {
            String sql = "SELECT c.name as college,\n" +
                    "       SUM(CASE WHEN s.sex = 0 THEN 1 ELSE 0 END) AS male_count,\n" +
                    "       SUM(CASE WHEN s.sex = 1 THEN 1 ELSE 0 END) AS female_count\n" +
                    "FROM student s\n" +
                    "INNER JOIN college c ON s.college_id = c.id\n" +
                    "GROUP BY c.name;";
            List<genderRatioDTO> genderRario = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(genderRatioDTO.class));
            return Result.ok(genderRario);
        }catch (Exception e){
            return  Result.fail(ResultCodeEnum.DATA_ERROR);
        }
    }
}
