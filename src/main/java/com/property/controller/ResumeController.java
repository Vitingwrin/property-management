package com.property.controller;

import com.property.controller.result.Result;
import com.property.pojo.*;
import com.property.service.ResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/1 18:05
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {

    private final ResumeService resumeService;
    private static Logger logger = LoggerFactory.getLogger(ResumeController.class);
    private final static Integer NEW = -1;
    private final HttpServletRequest request;

    @Autowired
    public ResumeController(ResumeService resumeService, HttpServletRequest request) {
        this.resumeService = resumeService;
        this.request = request;
    }

    @GetMapping("/getResumeByUserId")
    public Result getResumeByUserId(@RequestParam("userId") Integer userId) {
        try {
            return Result.success().add("resume", resumeService.findResumeByUserId(userId));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @GetMapping("/getResumeByResumeId")
    public Result getResumeByResumeId(@RequestParam("resumeId") Integer resumeId) {
        try {
            return Result.success().add("resume", resumeService.findResumeByResumeId(resumeId));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @GetMapping("/getBaseInfoByUserId")
    public Result getBaseInfoByUserId(@RequestParam("userId") Integer userId) {
        try {
            return Result.success().add("baseInfo", resumeService.findBaseInfoByUserId(userId));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @PostMapping("/saveResume")
    public Result saveResume(@RequestBody Resume resume) {
        try {
            resume.setBirthday(resume.getBirthday().substring(0, 10));
            // 无id插入，有id更新
            if (resume.getId() == null) {
                resumeService.insertResume(resume);
            } else {
                resumeService.updateResumeById(resume);
            }
            return Result.success("基本信息保存成功！");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @PostMapping("/saveEducation")
    public Result saveEducation(@RequestBody(required = false) Collection<Education> educations) {
        if (educations == null || educations.isEmpty()) {
            return Result.success();
        }
        try {
            for (Education education : educations) {
                if (education.getId().equals(NEW)) {
                    resumeService.insertEducation(education);
                } else {
                    resumeService.updateEducation(education);
                }
            }
            return Result.success("保存成功");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @DeleteMapping("/deleteEducation")
    public Result deleteEducation(@RequestBody(required = false) Collection<Education> educations) {
        if (educations == null || educations.isEmpty()) {
            return Result.success();
        }
        List<Integer> ids = new ArrayList<>();
        for (Education education : educations) {
            ids.add(education.getId());
        }
        try {
            resumeService.deleteEducations(ids);
            return Result.success("删除成功");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @PostMapping("/saveExperience")
    public Result saveExperience(@RequestBody(required = false) Collection<Experience> experiences) {
        if (experiences == null || experiences.isEmpty()) {
            return Result.success();
        }
        try {
            for (Experience experience : experiences) {
                if (experience.getId().equals(NEW)) {
                    resumeService.insertExperience(experience);
                } else {
                    resumeService.updateExperience(experience);
                }
            }
            return Result.success("保存成功");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @DeleteMapping("/deleteExperience")
    public Result deleteExperience(@RequestBody(required = false) Collection<Experience> experiences) {
        if (experiences == null || experiences.isEmpty()) {
            return Result.success();
        }
        List<Integer> ids = new ArrayList<>();
        for (Experience experience : experiences) {
            ids.add(experience.getId());
        }
        try {
            resumeService.deleteExperiences(ids);
            return Result.success("删除成功");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @PostMapping("/saveAward")
    public Result saveAward(@RequestBody(required = false) Collection<Award> awards) {
        if (awards == null || awards.isEmpty()) {
            return Result.success();
        }
        try {
            for (Award award : awards) {
                if (award.getId().equals(NEW)) {
                    resumeService.insertAward(award);
                } else {
                    resumeService.updateAward(award);
                }
            }
            return Result.success("保存成功");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @DeleteMapping("/deleteAward")
    public Result deleteAward(@RequestBody(required = false) Collection<Award> awards) {
        if (awards == null || awards.isEmpty()) {
            return Result.success();
        }
        List<Integer> ids = new ArrayList<>();
        for (Award award : awards) {
            ids.add(award.getId());
        }
        try {
            resumeService.deleteAwards(ids);
            return Result.success("删除成功");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @GetMapping({"/getEducation", "/getExperience", "/getAward"})
    public Result getContents(@RequestParam("resumeId") String resumeId) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = request.getServletPath().substring(request.getServletPath().lastIndexOf('/') + 1);
        Class<? extends ResumeService> clazz = resumeService.getClass();
        Method method = clazz.getMethod(methodName, Integer.class);
        return Result.success().add("contents", method.invoke(resumeService, Integer.parseInt(resumeId)));
    }

    @PostMapping("/saveEvaluation")
    public Result saveEvaluation(@RequestParam("evaluation") String evaluation, @RequestParam("resumeId") Integer resumeId) {
        try {
            resumeService.updateEvaluation(evaluation, resumeId);
            return Result.success("保存成功");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @PostMapping("/sendResume")
    public Result sendResume(@RequestParam("userId") Integer userId, @RequestParam("jobId") Integer jobId) {
        Resume resume = resumeService.findResumeByUserId(userId);
        boolean needComplete = resume == null || resume.getEducations() == null || resume.getEducations().isEmpty()
                || resume.getExperiences() == null || resume.getExperiences().isEmpty();
        if (needComplete) {
            return Result.error("请先完善简历");
        }
        if (resumeService.isDupJobResume(jobId, resume.getId()) > 0) {
            return Result.error("你已投递该简历");
        }
        resumeService.insertJobResume(jobId, resume.getId());
        return Result.success("投递成功");
    }

    @DeleteMapping("/cancelResume")
    public Result cancelResume(@RequestParam("id") Integer id) {
        try {
            resumeService.deleteJobResume(id);
            return Result.success("成功取消投递");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    @PostMapping("toNext")
    public Result toNext(@RequestBody JobResume jobResume) {
        if (jobResume.getStep() >= 4) {
            return Result.error("已到达最后一阶段");
        }
        try {
            jobResume.setStep(jobResume.getStep() + 1);
            resumeService.updateStep(jobResume);
            return Result.success("成功进入下一阶段").add("jobResume", jobResume);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

}
