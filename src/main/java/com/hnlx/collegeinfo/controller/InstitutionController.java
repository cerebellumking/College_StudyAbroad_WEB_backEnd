package com.hnlx.collegeinfo.controller;

import com.hnlx.collegeinfo.entity.param.institution.InstitutionListParam;
import com.hnlx.collegeinfo.entity.param.institution.InstitutionPostParam;
import com.hnlx.collegeinfo.entity.returnning.ResultData;
import com.hnlx.collegeinfo.service.InstitutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: qxh
 * @Date: 2022/12/12
 * @Description: 机构信息控制器
 */
@Tag(name = "机构信息相关")
@RestController
@RequestMapping("institution")
public class InstitutionController {
    @Resource
    private InstitutionService institutionService;

    @Operation(summary = "根据id获取机构详情")
    @GetMapping("{institution_id}")
    public ResultData<Object> getInstitutionDetail(@PathVariable("institution_id") int id){
        Object obj = institutionService.getInstitutionById(id);
        return new ResultData<>().sendObj(true,obj);
    }

    @Operation(summary = "获取机构列表")
    @GetMapping("list")
    public ResultData<Object> getInstitutionList(InstitutionListParam param){
        Object obj = institutionService.getInstitutionList(param);
        return new ResultData<>().sendObj(true,obj);
    }

    @Operation(summary = "添加机构信息")
    @PostMapping
    public ResultData<Object> addInstitution(@RequestBody InstitutionPostParam param){
        Object obj = institutionService.createInstitution(param);
        if(obj == null){
            return new ResultData<>().FAILED();
        } else {
            return new ResultData<>().sendObj(true, obj);
        }
    }

    @Operation(summary = "关注机构")
    @PostMapping("follow")
    public ResultData<Object> followInstitution(int user_id, int institution_id){
        Object obj = institutionService.followInstitution(user_id,institution_id);
        if((int)obj == -1){
            return new ResultData<>().FAILED();
        } else {
            return new ResultData<>().OK();
        }
    }
}
