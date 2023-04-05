package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.objects.UserSignupDto;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller managing user sign-up behaviour
 */
@Controller
@RequestMapping(path = "/signup")
public class SignupController {

    @Autowired
    UserRepository userRepository;

    Logger logger = LogManager.getLogger(SignupController.class);

    /**
     * Show user signup page
     *
     * @return Signup view
     */
    @GetMapping("")
    public String signupPage() {
        return "signup";
    }

    /**
     * Handle user submitting the "sign up" form
     *
     * @param userData Data containing a username, password and "confirm
     *                 password" string from the form
     * @return Success view
     */
    @PostMapping(path = "", consumes =
            {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public RedirectView signupAction(@Valid UserSignupDto userData) {

        // Deny signup if username or password are blank
        if (userData.getPassword().length() < 1 || userData.getUsername()
                .length() < 1) {
            logger.error("Username or password cannot be blank");
            return new RedirectView("/signup");
        }

        // Deny signup if passwords don't match
        if (!userData.getPassword().equals(userData.getMatchingPassword())) {
            logger.error("Passwords don't match");
            return new RedirectView("/signup");
        }

        // Deny signup if username is taken
        if (userRepository.findByUsername(userData.getUsername()).isPresent()) {
            logger.error("Username already taken.");
            return new RedirectView("/signup");
        }

        // Create password hash and add to user table
        String passwordHash = new BCryptPasswordEncoder().encode(
                userData.getPassword());
        User createdUser = new User(userData.getUsername(), passwordHash);
        userRepository.save(createdUser);
        logger.info("Created user " + userData.getUsername());

        return new RedirectView("/");
    }
}
