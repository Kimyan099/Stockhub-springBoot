package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    //public List<Stock> getStocksByClient(Client client);

    //public List<Stock> getStocksByClient_Email(String email);
    public List<Stock> getStocksByClient_Id(Long id);

    @Query(value="select s from Stock s where s.client.id= :id")
    public List<Stock> getStocksByLoggedInClient(@Param("id")Long id);

}
