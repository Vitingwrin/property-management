package com.property.service;

import com.property.mapper.PropertyMapper;
import com.property.pojo.Property;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/7 21:29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PropertyService {

    private final PropertyMapper propertyMapper;

    public PropertyService(PropertyMapper propertyMapper) {
        this.propertyMapper = propertyMapper;
    }

    public void insertProperty(Property property) {
        propertyMapper.insertProperty(property);
    }

    public void deleteProperty(Integer id) {
        propertyMapper.deleteProperty(id);
    }

    public void updateProperty(Property property) {
        propertyMapper.updateProperty(property);
    }

    public List<Property> getAllProperties() {
        return propertyMapper.getAllProperties();
    }

    public List<Property> getAllUnsoldProperties() {
        return propertyMapper.getAllUnsoldProperties();
    }

    public List<Property> getAllPropertiesByResidenceId(Integer id) {
        return propertyMapper.getAllPropertiesByResidenceId(id);
    }

    public boolean checkCodeUnique(String code) {
        return !propertyMapper.isExistsCode(code);
    }

    public int checkInProperty(Integer id) {
        return propertyMapper.checkInProperty(id);
    }

    public int checkOutProperty(Integer id) {
        return propertyMapper.checkOutProperty(id);
    }

    public Property getPropertyByUserId(Integer userId) {
        return propertyMapper.getPropertyByUserId(userId);
    }

    public Property getPropertyByRepairId(Integer repairId) {
        return propertyMapper.getPropertyByRepairId(repairId);
    }
}
