package com.esfandsoft.sysc4806project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/results")
public class ResultsController {

    /**
     * Default Page for Results
     * <p>
     * Shows a page containing surveys which the user can view results for.
     *
     * @param model the page model
     * @return the view to use for this page
     * @author Nicholas Sendyk, 101143602
     */
    @GetMapping("")
    public String viewAllPage(Model model) {
        return "view_all_closed";
    }

    /**
     * Page to display the Results given a specific survey id
     * <p>
     *
     * @param id    PATH VARIABLE - ID representing the survey to provide results for
     * @param model the page model
     * @return the view to use for this page
     * @author Nicholas Sendyk, 101143602
     */
    @GetMapping("/{id}")
    public String viewSurveyResults(@PathVariable long id, Model model) {

        return "survey_results";
    }


    /**
     * Page to display the functionality of results given a specific survey id
     * <p>
     *
     * @param model the page model
     * @return the view to use for this page
     * @author Nicholas Sendyk, 101143602
     */
    @GetMapping("/test")
    public String viewTestSurveyResults(Model model) {
        return "test";
    }

}
