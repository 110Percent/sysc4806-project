package com.esfandsoft.sysc4806project.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/survey")
public class ResponseController {

    /**
     * controller for survey html template
     *
     * @param id
     * @return String "survey"
     */
    @GetMapping("")
    public String surveyGET(@RequestParam(value = "id") long id) {
        return "survey";
    }


}
