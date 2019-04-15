package com.property.mapper;

import com.property.pojo.Property;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PropertyMapper {
    void insertProperty(Property property);
    void deleteProperty(Integer id);
    void updateProperty(Property property);
    List<Property> getAllProperties();
    List<Property> getAllUnsoldProperties();
    List<Property> getAllPropertiesByResidenceId(Integer id);
    boolean isExistsCode(String code);
    int checkInProperty(Integer id);
    int checkOutProperty(Integer id);
    Property getPropertyByUserId(Integer userId);
    Property getPropertyByRepairId(Integer repairId);
}
