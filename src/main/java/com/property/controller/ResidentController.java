package com.property.controller;

import com.property.controller.result.Result;
import com.property.pojo.Resident;
import com.property.service.PropertyService;
import com.property.service.ResidentService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/9 22:03
 */
@RestController
@RequestMapping("/resident")
public class ResidentController {

    private final ResidentService residentService;
    private final PropertyService propertyService;

    public ResidentController(ResidentService residentService, PropertyService propertyService) {
        this.residentService = residentService;
        this.propertyService = propertyService;
    }

    @DeleteMapping("/checkOutProperty")
    public Result deleteResident(Integer propertyId) {
        residentService.deleteResident(residentService.getResidentByPropertyId(propertyId).getId());
        propertyService.checkOutProperty(propertyId);
        return Result.success("退住成功");
    }

    @PostMapping("/saveResident")
    public Result saveResident(@RequestBody Resident resident) {
        if (resident.getId() == null) {
            residentService.insertResident(resident);
            // 将房产状态改为已售
            propertyService.checkInProperty(Integer.parseInt(resident.getProperty()));
        } else {
            residentService.updateResident(resident);
        }
        return Result.success("入住成功");
    }

    @GetMapping("/getAllResidents")
    public Result getAllResidents() {
        return Result.success().add("residents", residentService.getAllResidents());
    }


}
