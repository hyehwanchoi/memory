package com.date.memory.web;

import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import com.date.memory.config.auth.SecurityConfig;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
// @WebMvcTest(controllers = HelloController.class,
//     excludeFilters = {
//         @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
//     }
// )

public class HelloControllerTest extends AbstractControllerTest {

    @WithMockUser(roles = "USER")
    @Test
    public void goodDate() throws Exception {
        String date = "Good Date";

        mvc.perform(get("/date"))
            .andExpect(status().isOk())
            .andExpect(content().string(date))
            .andDo(print());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void junit5Test() throws Exception {
        assertEquals(2, calculator(1,1), "Yes");
        assertAll("Heading", 
                    () -> assertEquals("John", "John"),
                    () -> {
                        assertAll("Heading",
                            () -> assertEquals("Test", "Test"));
                    });
    }

    public int calculator(int a, int b) {
        return a+b;
    }
}
    