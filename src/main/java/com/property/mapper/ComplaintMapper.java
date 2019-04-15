package com.property.mapper;

import com.property.pojo.Complaint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ComplaintMapper {
    int insertComplaint(Complaint complaint);
    int deleteComplaint(Integer id);
    int updateComplaint(Complaint complaint);
    List<Complaint> getComplaintsByUserId(Integer userId);
    List<Complaint> getAllComplaints();
}
