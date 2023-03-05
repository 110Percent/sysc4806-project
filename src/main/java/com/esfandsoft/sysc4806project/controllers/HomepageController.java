package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller managing the landing page (for non-logged-in users)
 * and dashboard (for logged-in users)
 */
@Controller
public class HomepageController {

    @Autowired
    UserRepository userRepository;

    Logger logger = LogManager.getLogger(HomepageController.class);

    /**
     * Show either the landing page or a populated dashboard, depending on
     * whether the user is logged in
     *
     * @param session Session object for the user
     * @param model   Model to edit when displaying the dashboard
     * @return Either the landing or dashboard view
     */
    @GetMapping("/")
    public String homePage(HttpSession session, Model model) {
        // If not logged in, send user to the landing page
        if (session.getAttribute("username") == null) {
            return "landing";
        }

        // Get the user from the database to load the list of surveys
        Optional<User> fetchedUser = userRepository.findByUsername(String.valueOf(session.getAttribute("username")));
        if (fetchedUser.isEmpty()) {
            // This shouldn't happen, but just in case...
            logger.error("User was logged in with username" + session.getAttribute("username") + ", but the user entry was not found in the database?");
            logger.info("Invalidating session for user");
            session.invalidate();
            return "landing";
        }

        // Get username and surveys to pass to the dashboard page
        User user = fetchedUser.get();
        model.addAttribute("username", user.getUsername());
        ArrayList<Survey> surveys = new ArrayList<>(user.getSurveys());
        model.addAttribute("surveys", surveys);

        return "dashboard";
    }
}
