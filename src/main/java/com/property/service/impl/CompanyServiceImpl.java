package com.property.service.impl;

import com.property.mapper.CompanyMapper;
import com.property.pojo.Company;
import com.property.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chichiu Yeung
 * Created in 2019/3/16 23:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl implements CompanyService {

    private CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    @Override
    public int insertCompany(Company company) {
        return companyMapper.insertCompany(company);
    }

    @Override
    public Company findCompanyByName(String name) {
        return companyMapper.findCompanyByName(name);
    }

    @Override
    public Integer getCompanyCount() {
        return companyMapper.getCompanyCount();
    }

    @Override
    public List<Company> findCompanyByPaging(Integer page) {
        return companyMapper.findCompanyByPaging((page - 1) * 10);
    }

    @Override
    public Company findCompanyById(Integer id) {
        return companyMapper.findCompanyById(id);
    }

    @Override
    public int updateCompany(Company company) {
        return companyMapper.updateCompany(company);
    }

    @Override
    public int deleteCompany(Integer id) {
        return companyMapper.deleteCompany(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyMapper.getAllCompanies();
    }

    @Override
    public int getCompanyId(String name) {
        return companyMapper.getCompanyId(name);
    }

    @Override
    public List<Company> getLatestCompanies() {
        return companyMapper.getLatestCompanies();
    }
}
