package com.group13.nsrs.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group13.nsrs.model.dto.DormiRequireDto;
import com.group13.nsrs.model.entity.DormiRequirement;
import com.group13.nsrs.model.entity.User;
import com.group13.nsrs.service.DormiRequirementService;
import com.group13.nsrs.mapper.DomiRequirementMapper;
import com.group13.nsrs.util.result.Result;
import com.group13.nsrs.util.result.ResultCodeEnum;
import com.group13.nsrs.util.thread.ThreadLocalUtil;
import org.springframework.stereotype.Service;

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
        DormiRequirement dormiRequirement = BeanUtil.copyProperties(dormiRequireDto, DormiRequirement.class);
        dormiRequirement.setSnumber(user.getSnumber());
        this.save(dormiRequirement);
        return Result.ok();
    }
}




