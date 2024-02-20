package com.example.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/u")
public class HomeController {
    @GetMapping
    public String profile() {
        return "";
    }
}
