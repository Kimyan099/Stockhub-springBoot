package com.codecool.stockhub;

import com.codecool.stockhub.model.UserObject;
import com.codecool.stockhub.service.UserList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class UserObjectListTest {

    @Autowired
    UserList userList;


    @Test
    void testIsUserExistAfterRegistering() {
        UserObject userObject = Mockito.mock(UserObject.class);
        userList.registerUser(userObject);
        assertEquals(1, userList.getUsers().size());
    }
}
