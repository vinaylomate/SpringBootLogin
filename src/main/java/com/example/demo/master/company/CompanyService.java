package com.example.demo.master.company;

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
        company.setCompanyName(company.getCompanyName().trim());
        company.setCompanyDescription(company.getCompanyDescription().trim());
        company.setCompanyCode(company.getCompanyCode().trim());
        if(company.getCompanyName().length() == 0 ||
        company.getCompanyDescription().length() == 0 ||
        company.getCompanyCode().length() == 0) {
            throw new IllegalStateException("Invalid Inputs");
        }
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
}
