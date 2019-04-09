package com.property.mapper;

import com.property.pojo.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompanyMapper {

    int insertCompany(Company company);
    Company findCompanyByName(String name);
    Integer getCompanyCount();
    List<Company> findCompanyByPaging(Integer offset);
    Company findCompanyById(Integer id);
    int updateCompany(Company company);
    int deleteCompany(Integer id);
    List<Company> getAllCompanies();
    int getCompanyId(String name);
    List<Company> getLatestCompanies();
}
