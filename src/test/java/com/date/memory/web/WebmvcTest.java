package com.date.memory.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

@WebMvcTest(controllers = Webmvc.class)
public class WebmvcTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void WebmvcPrintTest() throws Exception{
        String test = "HelloWorld";

        mvc.perform(get("/webmvc"))
            .andExpect(status().isOk())
            .andExpect(content().string(test));
    }
}
