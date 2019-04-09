package com.property.controller;

import com.property.controller.result.Result;
import com.property.pojo.Residence;
import com.property.pojo.Resident;
import com.property.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/7 18:49
 */
@RestController
@RequestMapping("/residence")
public class ResidenceController {

    private final ResidenceService residenceService;

    public ResidenceController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }

    @DeleteMapping("/deleteResidence")
    public Result deleteResidence(Integer id) {
        residenceService.deleteResidence(id);
        return Result.success("删除成功");
    }

    @PostMapping("/saveResidence")
    public Result saveResidence(@RequestBody Residence residence) {
        if (residence.getId() == null) {
            residenceService.insertResidence(residence);
        } else {
            residenceService.updateResidence(residence);
        }
        return Result.success("保存成功");
    }

    @GetMapping("/getAllResidences")
    public Result getAllResidences() {
        return Result.success().add("residences", residenceService.getAllResidences());
    }

    @GetMapping("/getAllCheckedResidences")
    public Result getAllCheckedResidences() {
        return Result.success().add("residences", residenceService.getAllCheckedResidences());
    }

    @GetMapping("/checkCodeUnique")
    public Result checkCodeUnique(String code) {
        return residenceService.checkCodeUnique(code) ? Result.success() : Result.error("编号已存在");
    }

}
