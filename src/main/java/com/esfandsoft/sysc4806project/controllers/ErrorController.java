package com.esfandsoft.sysc4806project.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller managing any error pages returned to the user
 */
@Controller
public class ErrorController {

    /**
     * Display error screen and link to return to dashboard
     *
     * @return error page
     */
    @GetMapping("/error")
    public String homePage(HttpSession session, Model model) {
        return "error";
    }
}
