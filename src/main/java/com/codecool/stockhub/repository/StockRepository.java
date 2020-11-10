package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.Client;
import com.codecool.stockhub.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    public List<Stock> getStocksByClient(Client client);

}
