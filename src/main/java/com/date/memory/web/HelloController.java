package com.date.memory.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @GetMapping("/date")
    public String goodDate() {
        return "GoodDate";
    }
}