package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(path = "/logout")
public class LogoutController {
    @Autowired
    UserRepository userRepository;

    Logger logger = LogManager.getLogger(SignupController.class);

    @GetMapping("")
    public RedirectView loginPage(HttpSession session) {
        if (session.getAttribute("username") != null) {
            logger.info("Logging out user " + session.getAttribute("username"));
            session.invalidate();
        }
        return new RedirectView("/");
    }
}
