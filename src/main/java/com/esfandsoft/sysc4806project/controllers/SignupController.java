package com.esfandsoft.sysc4806project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/signup")
public class SignupController {
    @GetMapping("")
    public String signupPage() {
        return "signup";

    }
}
