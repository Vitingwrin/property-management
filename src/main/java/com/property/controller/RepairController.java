package com.property.controller;

import com.property.controller.result.Result;
import com.property.pojo.Bill;
import com.property.pojo.Repair;
import com.property.service.BillService;
import com.property.service.PropertyService;
import com.property.service.RepairService;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/15 12:56
 */
@RestController
@RequestMapping("/repair")
public class RepairController {

    private final RepairService repairService;
    private final PropertyService propertyService;
    private final BillService billService;

    public RepairController(RepairService repairService, PropertyService propertyService, BillService billService) {
        this.repairService = repairService;
        this.propertyService = propertyService;
        this.billService = billService;
    }

    @GetMapping("/getRepairsByUserId")
    public Result getRepairsByUserId(Integer userId) {
        return Result.success().add("repairs", repairService.getRepairsByUserId(userId));
    }

    @PostMapping("/send")
    public Result send(@RequestBody Repair repair) {
        // 生成code
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String prefix = format.format(date);
        String suffix = propertyService.getPropertyByUserId(Integer.parseInt(repair.getCreator())).getId().toString();
        repair.setCode(prefix + suffix);
        repair.setStatus(0);
        repairService.insertRepair(repair);
        return Result.success("发起成功");
    }

    @GetMapping("/getAllRepairs")
    public Result getAllRepairs() {
        return Result.success().add("repairs", repairService.getAllRepairs());
    }

    @PostMapping("/fee")
    public Result fee(@RequestBody Repair repair) {
        repair.setStatus(1);
        repairService.updateRepair(repair);
        // 新增缴费单
        Bill bill = new Bill();
        bill.setFee(repair.getFee());
        bill.setName("报修单");
        bill.setRepairId(repair.getId());
        bill.setProperty(propertyService.getPropertyByRepairId(repair.getId()).getId().toString());
        billService.insertRepairBill(bill);
        return Result.success("反馈费用成功");
    }

    @PostMapping("/finish")
    public Result finish(@RequestBody Repair repair) {
        repair.setStatus(3);
        repairService.updateRepair(repair);
        return Result.success("处理成功");
    }
}
