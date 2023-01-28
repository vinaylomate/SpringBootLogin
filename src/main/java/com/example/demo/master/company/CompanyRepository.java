package com.example.demo.master.company;

import com.example.demo.master.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "SELECT * FROM company WHERE company_code = ?1", nativeQuery = true)
    Company findByCompanyCode(String companyCode);

    @Override
    List<Company> findAll();
}
