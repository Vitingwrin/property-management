package com.property.service;

import com.property.mapper.ResidentMapper;
import com.property.pojo.Resident;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/9 22:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResidentService {

    private final ResidentMapper residentMapper;

    public ResidentService(ResidentMapper residentMapper) {
        this.residentMapper = residentMapper;
    }


    public int insertResident(Resident resident) {
        return residentMapper.insertResident(resident);
    }

    public int deleteResident(Integer id) {
        return residentMapper.deleteResident(id);
    }

    public int updateResident(Resident resident) {
        return residentMapper.updateResident(resident);
    }

    public List<Resident> getAllResidents() {
        return residentMapper.getAllResidents();
    }

    public Resident getResidentByUserName(String userName) {
        return residentMapper.getResidentByUserName(userName);
    }

    public Resident getResidentById(Integer id) {
        return residentMapper.getResidentById(id);
    }

    public Resident getResidentByPropertyId(Integer id) {
        return residentMapper.getResidentByPropertyId(id);
    }

}
