package com.property.service;

import com.property.mapper.RepairMapper;
import com.property.pojo.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/15 12:53
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RepairService {

    private final RepairMapper repairMapper;

    public RepairService(RepairMapper repairMapper) {
        this.repairMapper = repairMapper;
    }

    public int insertRepair(Repair repair) {
        return repairMapper.insertRepair(repair);
    }
    public int deleteRepair(Integer id) {
        return repairMapper.deleteRepair(id);
    }
    public int updateRepair(Repair repair) {
        return repairMapper.updateRepair(repair);
    }
    public List<Repair> getRepairsByUserId(Integer userId) {
        return repairMapper.getRepairsByUserId(userId);
    }
    public List<Repair> getAllRepairs() {
        return repairMapper.getAllRepairs();
    }
    public Repair getRepairById(Integer id) {
        return repairMapper.getRepairById(id);
    }
}
