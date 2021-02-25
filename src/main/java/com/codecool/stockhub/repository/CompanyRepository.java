package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Long> {


    Company getCompanyBySymbol(String symbol);

}
