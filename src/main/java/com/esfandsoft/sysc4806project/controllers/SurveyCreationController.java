package com.esfandsoft.sysc4806project.controllers;


import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller managing user sign-up behaviour
 */
@Controller
@RequestMapping(path = "/surveyCreation")
public class SurveyCreationController {

    @Autowired
    UserRepository userRepository;

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Logger logger = LogManager.getLogger(SurveyCreationController.class);

    /**
     * Show user survey creation page
     *
     * @return survey creation view
     */
    @GetMapping("")
    public String surveyCreation() {
        return "surveyCreation";
    }
}

