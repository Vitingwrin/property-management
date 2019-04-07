package com.findjob.mapper;

import com.findjob.pojo.Experience;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExperienceMapper {
    int insertExperience(Experience experience);
    int deleteExperiences(List<Integer> list);
    int updateExperience(Experience experience);
    List<Experience> getExperience(Integer resumeId);
}
