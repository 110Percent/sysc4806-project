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
     *
     * Shows a page containing surveys which the user can view results for.
     *
     * @author Nicholas Sendyk, 101143602
     * @param model the page model
     * @return the view to use for this page
     */
    @GetMapping("")
    public String viewAllPage(Model model) {
        return "view_all_closed";
    }

    /**
     * Page to display the Results given a specific survey id
     *
     * TODO: This page will not implement properly as there are not yet results from surveys
     *
     * @author Nicholas Sendyk, 101143602
     * @param id PATH VARIABLE - ID representing the survey to provide results for
     * @param model the page model
     * @return the view to use for this page
     */
    @GetMapping("/{id}}")
    public String viewSurveyResults(@PathVariable long id, Model model) {

        return "survey_results";
    }


    /**
     * Page to display the functionality of results given a specific survey id
     *
     * TODO: This page will be removed later on after all results have been implemented
     *
     * @author Nicholas Sendyk, 101143602
     * @param model the page model
     * @return the view to use for this page
     */
    @GetMapping("/test")
    public String viewTestSurveyResults(Model model) {

        return "survey_results";
    }

}
