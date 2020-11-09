package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
