package com.codecool.stockhub;

import com.codecool.stockhub.model.User;
import com.codecool.stockhub.service.UserList;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ReflectionUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserListTest {

    @Autowired
    UserList userList;

    @Test
    void testIsUserExistValidEmailReturnsTrue() {
        //User mockedUser = Mockito.mock(User.class);
        //String email = ReflectionUtils.findField(User.getClass(), [Field name], [Field type]);
        //ReflectionUtils.makeAccessible(field);
        //ReflectionUtils.setField(field, a, [Field value]);
        //assertTrue(userList.isUserExist("foo"));
    }
}
