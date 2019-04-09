package com.property.controller;

import com.property.controller.result.Result;
import com.property.pojo.Property;
import com.property.pojo.Residence;
import com.property.service.PropertyService;
import com.property.service.ResidenceService;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/7 21:32
 */
@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;
    private final ResidenceService residenceService;

    public PropertyController(PropertyService propertyService, ResidenceService residenceService) {
        this.propertyService = propertyService;
        this.residenceService = residenceService;
    }

    @DeleteMapping("/deleteProperty")
    public Result deleteProperty(Integer id) {
        propertyService.deleteProperty(id);
        return Result.success("删除成功");
    }

    @PostMapping("/saveProperty")
    public Result saveProperty(@RequestBody Property property) {
        // 生成房产名称
        Residence residence;
        // 小区是名称时转换成主键
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(property.getResidence());
        if (!isNum.matches()) {
            residence = residenceService.getResidenceByName(property.getResidence());
            property.setResidence(String.valueOf(residence.getId()));
        } else {
            residence = residenceService.getResidenceById(Integer.parseInt(property.getResidence()));
        }
        property.setName(residence.getName() + property.getBuildings() + "栋" + property.getUnit() + "单元" + property.getDoorplate());
        if (property.getId() == null) {
            propertyService.insertProperty(property);
        } else {
            propertyService.updateProperty(property);
        }
        return Result.success("保存成功");
    }

    @GetMapping("/getAllProperties")
    public Result getAllProperties() {
        return Result.success().add("properties", propertyService.getAllProperties());
    }

    @GetMapping("/getAllUnsoldProperties")
    public Result getAllUnsoldProperties() {
        return Result.success().add("properties", propertyService.getAllUnsoldProperties());
    }

    @GetMapping("/checkCodeUnique")
    public Result checkCodeUnique(String code) {
        return propertyService.checkCodeUnique(code) ? Result.success() : Result.error("编号已存在");
    }

}
