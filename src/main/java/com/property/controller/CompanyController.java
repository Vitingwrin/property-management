package com.property.controller;

import com.property.controller.result.Result;
import com.property.pojo.Company;
import com.property.service.CompanyService;
import com.property.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/3/16 23:05
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private CompanyService companyService;
    private UserService userService;
    private static final Integer NEW = -1; // 区分新增企业的ID

    @Autowired
    public CompanyController(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    /**
     * 分页检索企业
     */
    @GetMapping("/getCompaniesByPaging")
    public Result getCompanyByPaging(@RequestParam("page") Integer page) {
        List<Company> list;
        Integer total;
        try {
            list = companyService.findCompanyByPaging(page);
            total = companyService.getCompanyCount();
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR + "(查询企业出错)");
        }
        Result result = Result.success();
        result.add("companies", list);
        result.add("total", total);
        return result;
    }

    /**
     * 获取所有企业信息
     * */
    @PostMapping("getAllCompanies")
    public Result getAllCompanies() {
        try {
            return Result.success().add("companies", companyService.getAllCompanies());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }

    /**
     * 保存企业信息
     * */
    @PostMapping("/saveCompany")
    public Result addCompany(@RequestBody Company company) {
        int result;
        try {
            if (NEW.equals(company.getId())) {
                String creator = company.getCreator();
                company.setCreator(userService.getUserIdByUsername(company.getCreator()).toString());
                company.setId(null);
                result = companyService.insertCompany(company);
                company.setCreator(creator);
            } else {
                result = companyService.updateCompany(company);
            }
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }

        return result == 1 ? Result.success("保存成功").add("company", company) : Result.error("保存失败");
    }

    /**
     * 检查企业名称
     * */
    @GetMapping("/checkCompanyName")
    public Result checkCompanyName(@RequestParam("name") String name) {
        return companyService.findCompanyByName(name) == null ? Result.success() : Result.error("名称已被占用");
    }

    /**
     * 根据名称获取企业信息
     * */
    @GetMapping("/getCompanyByName")
    public Result getCompanyByName(@RequestParam("name") String name) {
        return Result.success().add("company", companyService.findCompanyByName(name));
    }

    /**
     * 删除企业
     * */
    @DeleteMapping("/deleteCompany")
    public Result deleteCompany(@RequestParam("id") Integer id) {
        return companyService.deleteCompany(id) == 1 ? Result.success("删除成功") : Result.error("删除失败，请重试");
    }

    /**
     * 获取企业主键
     * */
    @GetMapping("/getCompanyId")
    public Result getCompanyId(@RequestParam("name") String name) {
        return Result.success().add("id", companyService.getCompanyId(name));
    }

    @GetMapping("/getLatestCompanies")
    public Result getLatestCompanies() {
        try {
            return Result.success().add("companies", companyService.getLatestCompanies());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            return Result.error(Result.DATABASE_ERROR);
        }
    }
}
