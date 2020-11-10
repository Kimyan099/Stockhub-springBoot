package com.codecool.stockhub;

import com.codecool.stockhub.service.HTTPConnection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HTTPConnectionTest {

    @Autowired
    HTTPConnection testHTTPConnection;


    @Test
    void testGetContentWithCorrectUrlInput() {
        assertDoesNotThrow(() -> testHTTPConnection.getContent("https://finnhub.io/api/v1/news?category=general&token=bu2rf9f48v6pqlhnnvtg"));
    }

    @Test
    void testGetContentWithNoInput_ShouldThrow_Exception() {
        assertThrows(IllegalArgumentException.class, () -> testHTTPConnection.getContent(""));
    }







}
