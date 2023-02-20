package com.esfandsoft.sysc4806project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

    @GetMapping("/")
    public String homePage() {
        return "landing";
    }
}
