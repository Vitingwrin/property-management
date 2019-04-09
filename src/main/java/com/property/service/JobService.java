package com.property.service;

import com.property.pojo.Job;
import com.property.pojo.JobResume;

import java.util.List;

public interface JobService {

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
