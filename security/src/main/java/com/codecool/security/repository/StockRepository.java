package com.codecool.security.repository;

import com.codecool.security.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> getStocksByClient_Id(Long id);

    @Query(value="select s from Stock s where s.client.id= :id")
    List<Stock> getStocksByLoggedInClient(@Param("id")Long id);

}
