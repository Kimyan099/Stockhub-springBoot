package com.codecool.companyservice.repository;


import com.codecool.companyservice.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Long> {


    Company getCompanyBySymbol(String symbol);

}
