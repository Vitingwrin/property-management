package com.findjob.mapper;

import com.findjob.pojo.JobResume;
import com.findjob.pojo.Resume;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ResumeMapper {
    Resume findResumeByUserId(Integer userId);
    int insertResume(Resume resume);
    int updateResumeById(Resume resume);
    Resume findBaseInfoByUserId(Integer userId);
    int updateEvaluation(@Param("evaluation") String evaluation, @Param("resumeId") Integer resumeId);
    int isDupJobResume(@Param("jobId") Integer jobId, @Param("resumeId") Integer resumeId);
    int insertJobResume(@Param("jobId") Integer jobId, @Param("resumeId") Integer resumeId);
    int deleteJobResume(Integer id);
    int updateStep(JobResume jobResume);
    Resume findResumeByResumeId(Integer resumeId);
}
