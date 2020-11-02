package com.date.memory.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Webmvc {
    
    @GetMapping("/webmvc")
    public String WebmvcPrint() {
        return "HelloWorld";
    }
}
