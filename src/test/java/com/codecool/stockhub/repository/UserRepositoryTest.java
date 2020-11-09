package com.codecool.stockhub.repository;

import com.codecool.stockhub.model.UserObject;
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
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveOneSimple(){

        UserObject test = UserObject.builder()
                .balance(10000)
                .email("test@codecool.com")
                .name("test")
                .password("1234")
                .build();

        userRepository.save(test);

        List<UserObject> userList = userRepository.findAll();

        assertEquals(1, userList.size());
    }

    @Test
    public void saveUniqueTwice(){
        assertThrows(DataIntegrityViolationException.class, () -> {
            UserObject test = UserObject.builder()
                    .balance(10000)
                    .email("test@codecool.com")
                    .name("test")
                    .password("1234")
                    .build();

            UserObject test2 = UserObject.builder()
                    .balance(10000)
                    .email("test@codecool.com")
                    .name("test2")
                    .password("12345")
                    .build();

            userRepository.saveAll(Arrays.asList(test, test2));
        });
    }

    @Test
    public void nameShouldBeNotNull(){
        assertThrows(DataIntegrityViolationException.class, () -> {
            UserObject test = UserObject.builder()
                    .balance(10000)
                    .email("test@codecool.com")
                    .password("1234")
                    .build();

            userRepository.save(test);
        });
    }

}