package com.property.controller;

import com.property.controller.result.Result;
import com.property.pojo.Complaint;
import com.property.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/15 10:45
 */
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/getComplaintsByUserId")
    public Result getComplaintsByUserId(Integer userId) {
        return Result.success().add("complaints", complaintService.getComplaintsByUserId(userId));
    }

    @PostMapping("/send")
    public Result send(@RequestBody Complaint complaint) {
        complaintService.insertComplaint(complaint);
        return Result.success("发起成功");
    }

    @GetMapping("/getAllComplaints")
    public Result getAllComplaints() {
        return Result.success().add("complaints", complaintService.getAllComplaints());
    }

    @PostMapping("/feedback")
    public Result feedback(@RequestBody Complaint complaint) {
        // 反馈时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = format.format(date);
        complaint.setFeedbackTime(time);
        complaintService.updateComplaint(complaint);
        return Result.success("反馈成功");
    }
}
