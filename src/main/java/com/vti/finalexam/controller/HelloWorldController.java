package com.vti.finalexam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/helloworld")
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello Spring: " + name;
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "goodbye Spring";
    }
}
