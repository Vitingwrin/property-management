package com.property.mapper;

import com.property.pojo.Education;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EducationMapper {
    int insertEducation(Education education);
    int deleteEducations(List<Integer> list);
    int updateEducation(Education education);
    List<Education> getEducation(Integer resumeId);
}
