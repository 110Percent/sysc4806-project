package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/results")
public class ResultsController {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;

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
    public String viewSurveyResults(@PathVariable long id) {
        // TODO: Ensure user has privileges to view the results page

        return "survey_results";
    }

}
