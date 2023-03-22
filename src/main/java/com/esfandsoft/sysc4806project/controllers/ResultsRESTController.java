package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/results")
public class ResultsRESTController {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;

    Logger logger = LogManager.getLogger(ResultsRESTController.class);

    @GetMapping("/{id}/json")
    public Survey addressBook(@PathVariable long id, HttpSession session) {
        // If not logged in, send user to the landing page
        if (session.getAttribute("username") == null) {
            return new Survey("");
        }

        // Get the user from the database to load the list of surveys
        Optional<User> fetchedUser = userRepository.findByUsername(String.valueOf(session.getAttribute("username")));
        if (fetchedUser.isEmpty()) {
            // This shouldn't happen, but just in case...
            logger.error("User was logged in with username" + session.getAttribute("username") +
                    ", but the user entry was not found in the database?");
            logger.info("Invalidating session for user");
            session.invalidate();
            return new Survey("");
        }

        Survey fetchedSurvey = surveyRepository.findById(id);
        if (fetchedUser.get().getSurveys().contains(fetchedSurvey)) {
            return fetchedSurvey;
        }

        return new Survey("");
    }

}
