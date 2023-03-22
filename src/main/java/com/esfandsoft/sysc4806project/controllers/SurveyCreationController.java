package com.esfandsoft.sysc4806project.controllers;


import com.esfandsoft.sysc4806project.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller managing user survey creation
 *
 * @author Ethan Houlahan, 101145675
 */
@Controller
@RequestMapping(path = "/surveyCreation")
public class SurveyCreationController {

    @Autowired
    UserRepository userRepository;

    /**
     * Show user survey creation page if logged in otherwise, return login
     *
     * @return webpage
     */
    @GetMapping("")
    public String surveyCreation(HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null) {
            return "signup";
        }
        return "surveyCreation";
    }
}

