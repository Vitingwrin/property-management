package com.property.mapper;

import com.property.pojo.Residence;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/7 18:46
 */
@Component
public interface ResidenceMapper {
    void insertResidence(Residence residence);
    void deleteResidence(Integer id);
    void updateResidence(Residence residence);
    List<Residence> getAllResidences();
    List<Residence> getAllCheckedResidences();
    boolean isExistsCode(String code);
    Residence getResidenceByName(String name);
    Residence getResidenceById(Integer id);

}
