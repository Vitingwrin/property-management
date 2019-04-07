package com.findjob.service;

import com.findjob.pojo.Company;

import java.util.List;

public interface CompanyService {

    int insertCompany(Company company);
    Company findCompanyByName(String name);
    Integer getCompanyCount();
    List<Company> findCompanyByPaging(Integer page);
    Company findCompanyById(Integer id);
    int updateCompany(Company company);
    int deleteCompany(Integer id);
    List<Company> getAllCompanies();
    int getCompanyId(String name);
    List<Company> getLatestCompanies();
}
