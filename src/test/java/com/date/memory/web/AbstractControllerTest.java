package com.date.memory.web;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public abstract class AbstractControllerTest {
    protected MockMvc mvc;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    private void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}