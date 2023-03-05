package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.objects.UserLoginDto;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    Logger logger = LogManager.getLogger(SignupController.class);

    /**
     * Show user the login page
     *
     * @return Login view
     */
    @GetMapping("")
    public String loginPage() {
        return "login";
    }

    /**
     * Handle a login attempt. Set the "username" session value and send the user
     * to the root page on successful login, otherwise send back to the login page.
     *
     * @param login   Login information DTO - username and password
     * @param session Session object for the user
     * @return Redirect to the desired page
     */
    @PostMapping(path = "", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView loginSubmit(UserLoginDto login, HttpSession session) {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(login.getUsername());

        // User not found
        if (user.isEmpty()) {
            logger.error("User " + login.getUsername() + " not found");
            return new RedirectView("/login");
        }

        // Check password
        if (BCrypt.checkpw(login.getPassword(), user.get().getPasswordHash())) {
            session.setAttribute("username", user.get().getUsername());
            logger.info("Logged in as " + user.get().getUsername());
            return new RedirectView("/");
        } else {
            logger.error("Incorrect password supplied for " + user.get().getUsername());
            return new RedirectView("/login");
        }
    }
}
