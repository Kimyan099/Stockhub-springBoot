package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    public void saveOneSimple() {

        Stock aapl = Stock.builder()
                .symbol("AAPL")
                .price(100)
                .build();

        stockRepository.save(aapl);

        List<Stock> stocks = stockRepository.findAll();

        assertEquals(1, stocks.size());
    }

    @Test
    public void symbolShouldBeNotNull() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            Stock aapl = Stock.builder()
                    .name("AAPL")
                    .price(100)
                    .build();

            stockRepository.save(aapl);
        });
    }

}