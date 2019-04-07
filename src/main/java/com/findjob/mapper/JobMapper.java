package com.findjob.mapper;

import com.findjob.pojo.Job;
import com.findjob.pojo.JobResume;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JobMapper {

    int insertJob(Job job);
    Job findJobById(Integer id);
    int getJobsCount();
    int updateJob(Job job);
    int deleteJob(Integer id);
    List<Job> getAllJobs();
    List<Job> getLatestJobs();
    List<Job> findJobsByKeyword(String keyword);
    List<JobResume> findJobResumeByUserId(Integer userId);
    List<JobResume> getAllJobResumes();
}
