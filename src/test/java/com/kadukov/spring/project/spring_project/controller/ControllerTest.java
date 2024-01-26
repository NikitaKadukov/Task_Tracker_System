package com.kadukov.spring.project.spring_project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void set() {
    }

    @Test
    void shouldFindTaskByName() throws Exception {
    }

}
