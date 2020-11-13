package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.Company;
import com.codecool.stockhub.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {


    public Company getCompanyBySymbol(String symbol);

}
