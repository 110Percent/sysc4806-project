package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping(path = "/results")
public class ResultsController {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;

    Logger logger = LogManager.getLogger(ResultsController.class);

    /**
     * Default Page for Results
     * <p>
     * Shows a page containing surveys which the user can view results for.
     *
     * @return the view to use for this page
     * @author Nicholas Sendyk, 101143602
     */
    @GetMapping("/all/{user_id}")
    public String viewAllPage(@PathVariable long user_id) {

        return "view_all_closed_surveys";
    }

    /**
     * Page to display the Results given a specific survey id
     * <p>
     *
     * @param id PATH VARIABLE - ID representing the survey to provide results for
     * @return the view to use for this page
     * @author Nicholas Sendyk, 101143602
     */
    @GetMapping("/{id}")
    public String viewSurveyResults(@PathVariable long id, HttpSession session, Model model) {

        // If not logged in, send user to the landing page
        if (session.getAttribute("username") == null) {
            return "redirect:/";
        }

        // Get the user from the database to load the list of surveys
        Optional<User> fetchedUser = userRepository.findByUsername(String.valueOf(session.getAttribute("username")));
        if (fetchedUser.isEmpty()) {
            // This shouldn't happen, but just in case...
            logger.error("User was logged in with username" + session.getAttribute("username") +
                    ", but the user entry was not found in the database?");
            logger.info("Invalidating session for user");
            session.invalidate();
            return "redirect:/";
        }

        // Get the users surveys from the database
        Collection<Survey> s = fetchedUser.get().getSurveys();
        ArrayList<Long> s_ids = new ArrayList<>();
        for (Survey s1 : s) {
            s_ids.add(s1.getId());
        }

        // if the user is the owner of the survey
        if (s_ids.contains(id)) {
            Survey fetchedSurvey = surveyRepository.findById(id);
            // if the survey is closed
            if (fetchedSurvey.getIsClosed()) {
                return "survey_results";
            }
        }

        User user = fetchedUser.get();
        model.addAttribute("username", user.getUsername());
        ArrayList<Survey> surveys = new ArrayList<>(user.getSurveys());
        model.addAttribute("surveys", surveys);

        return "redirect:/";
    }

}
