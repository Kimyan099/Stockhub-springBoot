package com.codecool.stockhub;

import com.codecool.stockhub.model.User;
import com.codecool.stockhub.service.UserList;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserListTest {

    @Autowired
    UserList userList;


    @Test
    void testIsUserExistAfterRegistering() {
        User user = Mockito.mock(User.class);
        userList.registerUser(user);
        assertEquals(1, userList.getUsers().size());
    }
}
