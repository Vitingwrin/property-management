package com.findjob.controller;

import com.findjob.controller.result.Result;
import com.findjob.pojo.Job;
import com.findjob.service.CompanyService;
import com.findjob.service.JobService;
import com.findjob.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Chichiu Yeung
 * Created in 2019/3/20 12:29
 */
@RestController
@RequestMapping("/job")
public class JobController {

    private final CompanyService companyService;
    private final JobService jobService;
    private static Logger logger = LoggerFactory.getLogger(JobController.class);


    @Autowired
    public JobController(CompanyService companyService, JobService jobService) {
        this.companyService = companyService;
        this.jobService = jobService;
    }

    /**
     * 发布职位信息
     * */
    @PostMapping("/release")
    public Result release(@RequestBody Job job) {
        int result;
        try {
            if (job.getId() == null) {
                result = jobService.insertJob(job);
            } else {
                // 企业是名称时转换成主键
                Pattern pattern = Pattern.compile("[0-9]*");
                Matcher isNum = pattern.matcher(job.getCompany());
                if (!isNum.matches()) {
                    job.setCompany(String.valueOf(companyService.getCompanyId(job.getCompany())));
                }
                result = jobService.updateJob(job);
            }
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
        return result == 1 ? Result.success("发布成功") : Result.error("发布失败");
    }

    /**
     * 获取所有职位信息
     * */
    @GetMapping("/getAllJobs")
    public Result getAllJobs() {
        try {
            return Result.success().add("jobs", jobService.getAllJobs()).add("total", jobService.getJobsCount());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    /**
     * 获取最新十条职位信息
     * */
    @GetMapping("/getLatestJobs")
    public Result getLatestJobs() {
        try {
            return Result.success().add("jobs", jobService.getLatestJobs());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    /**
     * 按关键字查询职位信息
     * */
    @GetMapping("/getJobsByKeyword")
    public Result getJobsByKeyword(@RequestParam("keyword") String keyword) {
        try {
            return Result.success().add("jobs", jobService.findJobsByKeyword(keyword));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    /**
     * 根据用户获取已应聘职位
     * */
    @GetMapping("/getJobResumeByUserId")
    public Result getJobResumeByUserId(@RequestParam("userId") Integer userId) {
        try {
            return Result.success().add("jobResumes", jobService.findJobResumeByUserId(userId));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    /**
     * 获取所有应聘信息
     * */
    @GetMapping("/getAllJobResumes")
    public Result getAllJobResumes() {
        try {
            return Result.success().add("jobResumes", jobService.getAllJobResumes());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    /**
     * 删除职位
     * */
    @DeleteMapping("/deleteJob")
    public Result deleteJob(@RequestParam("id") Integer id) {
        try {
            jobService.deleteJob(id);
            return Result.success("删除成功");
        } catch (DataIntegrityViolationException e) {
            return Result.error("该职位已被投递，不能删除");
        } catch (DataAccessException e1) {
            logger.error(e1.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }
}
