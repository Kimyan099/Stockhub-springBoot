package com.codecool.stockhub;

import com.codecool.stockhub.service.CompanyList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CompanyListTest {

    @Autowired
    CompanyList companyList;

    @Test
    void testFilterDataWithWrongJSONInput() {
        String content = "{ foo : foo }";
        assertThrows(IllegalArgumentException.class, () -> companyList.filterData(content));
    }

    @Test
    void testFilterDataWithEmptyJSONInput() {
        String content = "";
        assertThrows(IllegalArgumentException.class, () -> companyList.filterData(content));
    }
}
