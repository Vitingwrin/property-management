package com.property.service;

import com.property.mapper.ComplaintMapper;
import com.property.pojo.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/15 10:43
 */
@Service
public class ComplaintService {

    private final ComplaintMapper complaintMapper;

    public ComplaintService(ComplaintMapper complaintMapper) {
        this.complaintMapper = complaintMapper;
    }

    public int insertComplaint(Complaint complaint) {
        return complaintMapper.insertComplaint(complaint);
    }
    public int deleteComplaint(Integer id) {
        return complaintMapper.deleteComplaint(id);
    }
    public int updateComplaint(Complaint complaint) {
        return complaintMapper.updateComplaint(complaint);
    }
    public List<Complaint> getComplaintsByUserId(Integer userId) {
        return complaintMapper.getComplaintsByUserId(userId);
    }
    public List<Complaint> getAllComplaints() {
        return complaintMapper.getAllComplaints();
    }
}
