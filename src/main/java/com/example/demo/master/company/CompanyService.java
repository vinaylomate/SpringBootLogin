package com.example.demo.master.company;

import com.example.demo.master.category.Category;
import com.example.demo.master.company.Company;
import com.example.demo.master.company.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

     private final CompanyRepository companyRepository;

    public String addCompany(Company company) {
        if(companyRepository.findByCompanyCode(company.getCompanyCode()) != null) {
            throw new IllegalStateException("Company Already Exists");
        }
        companyRepository.save(company);
        return "Saved";
    }

    public List<Company> showAllCompany() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    public Company showCompany(String companyCode) {
        return companyRepository.findByCompanyCode(companyCode);
    }

    public String updateCompany(Long id, Company company) {
        Company updateCompany = companyRepository.findById(id).get();
        updateCompany.setCompanyCode(company.getCompanyCode());
        updateCompany.setCompanyName(company.getCompanyName());
        updateCompany.setCompanyDescription(company.getCompanyDescription());
        return "Company updated";
    }
}
