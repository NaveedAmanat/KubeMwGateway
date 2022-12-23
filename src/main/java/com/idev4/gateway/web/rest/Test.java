package com.idev4.gateway.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Test {


    @GetMapping("/hello")
    public String sayHello(@RequestParam("username") String username) {
        if (username != null)
            return "Hello Mr/Mrs. " + username;
        return "Hello Mr/Mrs. ";
    }
}
