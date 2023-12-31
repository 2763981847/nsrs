package com.group13.nsrs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.dto.DormiRequireDto;
import com.group13.nsrs.model.entity.DormiRequirement;
import com.group13.nsrs.model.entity.Dormitory;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.service.DormiRequirementService;
import com.group13.nsrs.mapper.DomiRequirementMapper;
import com.group13.nsrs.service.DormitoryService;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import com.group13.nsrs.util.thread.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Oreki
 * @description 针对表【domi_requirement】的数据库操作Service实现
 * @createDate 2023-08-22 15:51:56
 */
@Service
public class DormiRequirementServiceImpl extends ServiceImpl<DomiRequirementMapper, DormiRequirement>
        implements DormiRequirementService {

    @Override
    public Result<String> saveDormiRequire(DormiRequireDto dormiRequireDto) {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String snumber = user.getSnumber();
        DormiRequirement requirement = this.lambdaQuery()
                .eq(DormiRequirement::getSnumber, snumber)
                .one();
        BeanUtil.copyProperties(dormiRequireDto, requirement);
        this.saveOrUpdate(requirement);
        return Result.ok();
    }

    @Override
    public Result<List<DormiRequirement>> listByDormitory(Long dormitoryId) {
        List<DormiRequirement> dormiRequirements = this.lambdaQuery()
                .eq(DormiRequirement::getDormitoryId, dormitoryId)
                .list();
        return Result.ok(dormiRequirements);
    }

    @Override
    public Result<Boolean> isCheckedIn() {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String snumber = user.getSnumber();
        DormiRequirement dormiRequirement = this.lambdaQuery()
                .eq(DormiRequirement::getSnumber, snumber)
                .one();
        boolean isCheckedIn = dormiRequirement != null && dormiRequirement.getDormitoryId() != null;
        return Result.ok(isCheckedIn);
    }

    @Resource
    private DormitoryService dormitoryService;

    @Override
    public Result<Dormitory> getCheckedInInfo() {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String snumber = user.getSnumber();
        DormiRequirement dormiRequirement = this.lambdaQuery()
                .eq(DormiRequirement::getSnumber, snumber)
                .one();
        if (dormiRequirement == null || dormiRequirement.getDormitoryId() == null) {
            return Result.fail(ResultCodeEnum.FETCH_USERINFO_ERROR, "未查询到申请入住信息");
        }
        Long dormitoryId = dormiRequirement.getDormitoryId();
        Dormitory dormitory = dormitoryService.getById(dormitoryId);
        return Result.ok(dormitory);
    }

    @Override
    public Result<String> apply(Long dormitoryId) {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String snumber = user.getSnumber();
        DormiRequirement dormiRequirement = this.lambdaQuery()
                .eq(DormiRequirement::getSnumber, snumber)
                .one();
        if (dormiRequirement == null) {
            return Result.fail(ResultCodeEnum.FETCH_USERINFO_ERROR, "请先填写宿舍需求");
        }
        if (dormiRequirement.getDormitoryId() != null) {
            return Result.fail(ResultCodeEnum.FAIL, "已申请入住");
        }
        Dormitory dormitory = dormitoryService.getById(dormitoryId);
        if (dormitory.getCount() >= 4) {
            return Result.fail(ResultCodeEnum.PARAM_ERROR, "该宿舍已满");
        }
        dormitory.setCount(dormitory.getCount() + 1);
        dormitoryService.updateById(dormitory);
        dormiRequirement.setDormitoryId(dormitoryId);
        this.updateById(dormiRequirement);
        return Result.ok();
    }

    @Override
    public Result<String> cancel() {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String snumber = user.getSnumber();
        DormiRequirement dormiRequirement = this.lambdaQuery()
                .eq(DormiRequirement::getSnumber, snumber)
                .one();
        if (dormiRequirement == null || dormiRequirement.getDormitoryId() == null) {
            return Result.fail(ResultCodeEnum.DATA_NOT_EXIST, "未申请入住");
        }
        Dormitory dormitory = dormitoryService.lambdaQuery()
                .eq(Dormitory::getId, dormiRequirement.getDormitoryId())
                .one();
        dormitory.setCount(dormitory.getCount() - 1);
        dormitoryService.updateById(dormitory);
        this.lambdaUpdate()
                .eq(DormiRequirement::getId, dormiRequirement.getId())
                .set(DormiRequirement::getDormitoryId, null).update();
        return Result.ok();
    }

    @Override
    public Result<DormiRequirement> getByUser() {
        User user = ThreadLocalUtil.getUser();
        if (user == null) {
            return Result.fail(ResultCodeEnum.LOGIN_AURH);
        }
        String snumber = user.getSnumber();
        DormiRequirement dormiRequirement = this.lambdaQuery()
                .eq(DormiRequirement::getSnumber, snumber)
                .one();
        return Result.ok(dormiRequirement);
    }
}




