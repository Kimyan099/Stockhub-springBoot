package com.codecool.stockhub;

import com.codecool.stockhub.model.Client;
import com.codecool.stockhub.service.ClientList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClientListTest {

    @Autowired
    ClientList clientList;


    @Test
    void testIsUserExistAfterRegistering() {
        Client client = Mockito.mock(Client.class);
        clientList.registerUser(client);
        assertEquals(1, clientList.getUsers().size());
    }
}
