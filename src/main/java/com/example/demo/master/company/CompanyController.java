package com.example.demo.master.company;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping(path = "/company")
    public String addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @GetMapping(path = "/allCompany")
    public List<Company> showAllCompany() {

        return companyService.showAllCompany();
    }
}
