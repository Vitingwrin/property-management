package com.property.service;

import com.property.pojo.*;

import java.util.List;

public interface ResumeService {
    Resume findResumeByUserId(Integer userId);
    int insertResume(Resume resume);
    int updateResumeById(Resume resume);
    Resume findBaseInfoByUserId(Integer userId);
    int updateEvaluation(String evaluation, Integer resumeId);
    int isDupJobResume(Integer jobId, Integer resumeId);
    int insertJobResume(Integer jobId, Integer resumeId);
    int deleteJobResume(Integer id);
    int updateStep(JobResume jobResume);
    Resume findResumeByResumeId(Integer resumeId);

    int insertEducation(Education education);
    int deleteEducations(List<Integer> list);
    int updateEducation(Education education);
    List<Education> getEducation(Integer resumeId);

    int insertExperience(Experience experience);
    int deleteExperiences(List<Integer> list);
    int updateExperience(Experience experience);
    List<Experience> getExperience(Integer resumeId);

    int insertAward(Award award);
    int deleteAwards(List<Integer> list);
    int updateAward(Award award);
    List<Award> getAward(Integer resumeId);
}
