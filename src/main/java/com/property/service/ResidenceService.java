package com.property.service;

import com.property.mapper.ResidenceMapper;
import com.property.pojo.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/7 18:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResidenceService {

    private final ResidenceMapper residenceMapper;

    public ResidenceService(ResidenceMapper residenceMapper) {
        this.residenceMapper = residenceMapper;
    }

    public void insertResidence(Residence residence) {
        residenceMapper.insertResidence(residence);
    }

    public void deleteResidence(Integer id) {
        residenceMapper.deleteResidence(id);
    }

    public void updateResidence(Residence residence) {
        residenceMapper.updateResidence(residence);
    }

    public List<Residence> getAllResidences() {
        return residenceMapper.getAllResidences();
    }

    public List<Residence> getAllCheckedResidences() {
        return residenceMapper.getAllCheckedResidences();
    }

    public boolean checkCodeUnique(String code) {
        return !residenceMapper.isExistsCode(code);
    }

    public Residence getResidenceByName(String name) {
        return residenceMapper.getResidenceByName(name);
    }

    public Residence getResidenceById(Integer id) {
        return residenceMapper.getResidenceById(id);
    }
}

