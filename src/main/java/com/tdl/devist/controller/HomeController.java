package com.tdl.devist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/home")
public class HomeController {
    @RequestMapping
    public String home() {
        return "home";
    }
}