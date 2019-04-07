package com.findjob.service.impl;

import com.findjob.mapper.JobMapper;
import com.findjob.pojo.Job;
import com.findjob.pojo.JobResume;
import com.findjob.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/3/20 12:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {

    private final JobMapper jobMapper;

    @Autowired
    public JobServiceImpl(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Override
    public int insertJob(Job job) {
        return jobMapper.insertJob(job);
    }

    @Override
    public Job findJobById(Integer id) {
        return jobMapper.findJobById(id);
    }

    @Override
    public int getJobsCount() {
        return jobMapper.getJobsCount();
    }

    @Override
    public int updateJob(Job job) {
        return jobMapper.updateJob(job);
    }

    @Override
    public int deleteJob(Integer id) {
        return jobMapper.deleteJob(id);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobMapper.getAllJobs();
    }

    @Override
    public List<Job> getLatestJobs() {
        return jobMapper.getLatestJobs();
    }

    @Override
    public List<Job> findJobsByKeyword(String keyword) {
        return jobMapper.findJobsByKeyword(keyword);
    }

    @Override
    public List<JobResume> findJobResumeByUserId(Integer userId) {
        return jobMapper.findJobResumeByUserId(userId);
    }

    @Override
    public List<JobResume> getAllJobResumes() {
        return jobMapper.getAllJobResumes();
    }
}
