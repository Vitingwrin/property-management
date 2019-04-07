package com.findjob.service.impl;

import com.findjob.mapper.AwardMapper;
import com.findjob.mapper.EducationMapper;
import com.findjob.mapper.ExperienceMapper;
import com.findjob.mapper.ResumeMapper;
import com.findjob.pojo.*;
import com.findjob.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/4/1 18:04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResumeServiceImpl implements ResumeService {

    private final ResumeMapper resumeMapper;
    private final EducationMapper educationMapper;
    private final ExperienceMapper experienceMapper;
    private final AwardMapper awardMapper;

    @Autowired
    public ResumeServiceImpl(ResumeMapper resumeMapper, EducationMapper educationMapper, ExperienceMapper experienceMapper, AwardMapper awardMapper) {
        this.resumeMapper = resumeMapper;
        this.educationMapper = educationMapper;
        this.experienceMapper = experienceMapper;
        this.awardMapper = awardMapper;
    }

    @Override
    public Resume findResumeByUserId(Integer userId) {
        return resumeMapper.findResumeByUserId(userId);
    }

    @Override
    public int insertResume(Resume resume) {
        return resumeMapper.insertResume(resume);
    }

    @Override
    public int updateResumeById(Resume resume) {
        return resumeMapper.updateResumeById(resume);
    }

    @Override
    public Resume findBaseInfoByUserId(Integer userId) {
        return resumeMapper.findBaseInfoByUserId(userId);
    }

    @Override
    public int updateEvaluation(String evaluation, Integer resumeId) {
        return resumeMapper.updateEvaluation(evaluation, resumeId);
    }

    @Override
    public int isDupJobResume(Integer jobId, Integer resumeId) {
        return resumeMapper.isDupJobResume(jobId, resumeId);
    }

    @Override
    public int insertJobResume(Integer jobId, Integer resumeId) {
        return resumeMapper.insertJobResume(jobId, resumeId);
    }

    @Override
    public int deleteJobResume(Integer id) {
        return resumeMapper.deleteJobResume(id);
    }

    @Override
    public int updateStep(JobResume jobResume) {
        return resumeMapper.updateStep(jobResume);
    }

    @Override
    public Resume findResumeByResumeId(Integer resumeId) {
        return resumeMapper.findResumeByResumeId(resumeId);
    }

    @Override
    public int insertEducation(Education education) {
        return educationMapper.insertEducation(education);
    }

    @Override
    public int deleteEducations(List<Integer> list) {
        return educationMapper.deleteEducations(list);
    }

    @Override
    public int updateEducation(Education education) {
        return educationMapper.updateEducation(education);
    }

    @Override
    public List<Education> getEducation(Integer resumeId) {
        return educationMapper.getEducation(resumeId);
    }

    @Override
    public int insertExperience(Experience experience) {
        return experienceMapper.insertExperience(experience);
    }

    @Override
    public int deleteExperiences(List<Integer> list) {
        return experienceMapper.deleteExperiences(list);
    }

    @Override
    public int updateExperience(Experience experience) {
        return experienceMapper.updateExperience(experience);
    }

    @Override
    public List<Experience> getExperience(Integer resumeId) {
        return experienceMapper.getExperience(resumeId);
    }

    @Override
    public int insertAward(Award award) {
        return awardMapper.insertAward(award);
    }

    @Override
    public int deleteAwards(List<Integer> list) {
        return awardMapper.deleteAwards(list);
    }

    @Override
    public int updateAward(Award award) {
        return awardMapper.updateAward(award);
    }

    @Override
    public List<Award> getAward(Integer resumeId) {
        return awardMapper.getAward(resumeId);
    }
}
