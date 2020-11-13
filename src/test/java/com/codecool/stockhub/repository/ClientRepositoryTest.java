package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.Client;
import com.codecool.stockhub.model.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private StockRepository stockRepository;


    @Test
    public void saveOneSimple(){

        Client test = Client.builder()
                .balance(10000)
                .email("test@codecool.com")
                .name("test")
                .password("1234")
                .build();

        clientRepository.save(test);

        List<Client> userList = clientRepository.findAll();

        assertEquals(1, userList.size());
    }

    @Test
    public void saveUniqueTwice(){
        assertThrows(DataIntegrityViolationException.class, () -> {
            Client test = Client.builder()
                    .balance(10000)
                    .email("test@codecool.com")
                    .name("test")
                    .password("1234")
                    .build();

            Client test2 = Client.builder()
                    .balance(10000)
                    .email("test@codecool.com")
                    .name("test2")
                    .password("12345")
                    .build();

            clientRepository.saveAll(Arrays.asList(test, test2));
        });
    }

    @Test
    public void nameShouldBeNotNull(){
        assertThrows(DataIntegrityViolationException.class, () -> {
            Client test = Client.builder()
                    .balance(10000)
                    .email("test@codecool.com")
                    .password("1234")
                    .build();

            clientRepository.save(test);
        });
    }

    @Test
    public void addStockToClient(){
        clientRepository.deleteAll();

        Stock aapl = Stock.builder()
                .symbol("AAPL")
                .price(100)
                .build();

        Client client = Client.builder()
                .balance(10000)
                .email("test@codecool.com")
                .name("client")
                .password("1234")
                .stock(aapl)
                .build();

        aapl.setClient(client);
        clientRepository.save(client);

        List<Stock> stocks = stockRepository.findAll();

        assertEquals(1,stocks.size());

    }
}