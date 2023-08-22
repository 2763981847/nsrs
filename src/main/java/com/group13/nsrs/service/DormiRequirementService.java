package com.group13.nsrs.service;

import com.group13.nsrs.model.dto.DormiRequireDto;
import com.group13.nsrs.model.entity.DormiRequirement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.group13.nsrs.model.entity.Dormitory;
import com.group13.nsrs.util.result.Result;

import java.util.List;

/**
* @author Oreki
* @description 针对表【domi_requirement】的数据库操作Service
* @createDate 2023-08-22 15:51:56
*/
public interface DormiRequirementService extends IService<DormiRequirement> {

    Result<String> saveDormiRequire(DormiRequireDto dormiRequireDto);

    Result<List<DormiRequirement>> listByDormitory(Long dormitoryId);

    Result<Boolean> isCheckedIn();

    Result<Dormitory> getCheckedInInfo();

    Result<String> apply(Long dormitoryId);

    Result<String> cancel();

}
