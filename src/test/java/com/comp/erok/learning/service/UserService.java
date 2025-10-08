package com.comp.erok.learning.service;

import com.comp.erok.learning.Repository.userRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserService {
    @Autowired
    private userRepository userRepository;

    @ParameterizedTest
    @CsvSource({
        "ram",
        "shyam",
        "erok"
    })
    public void testFindByUserName(String userName){
        //assertEquals("ram", userRepository.findByUserName("ram"));
        assertNotNull(userRepository.findByUserName(userName),"Test Failed" + userName);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1, 2",
        "2, 3, 5",
        "3, 5, 8"
    })
    public void parameter(int a, int b, int sum){
        assertEquals(sum , a+b);
    }
}
