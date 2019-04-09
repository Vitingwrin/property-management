package com.property.mapper;

import com.property.pojo.Residence;
import com.property.pojo.Resident;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ResidentMapper {
    int insertResident(Resident resident);
    int deleteResident(Integer id);
    int updateResident(Resident resident);
    List<Resident> getAllResidents();
    Resident getResidentByUserName(String userName);
    Resident getResidentById(Integer id);
    Resident getResidentByPropertyId(Integer id);
}
