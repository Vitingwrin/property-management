package com.property.mapper;

import com.property.pojo.Repair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RepairMapper {
    int insertRepair(Repair repair);
    int deleteRepair(Integer id);
    int updateRepair(Repair repair);
    List<Repair> getRepairsByUserId(Integer userId);
    List<Repair> getAllRepairs();
    Repair getRepairById(Integer id);
}
