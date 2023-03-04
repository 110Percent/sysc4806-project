package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.esfandsoft.sysc4806project.entities.Survey;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/survey")
public class SurveyRESTController {

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping("")
    public Survey surveyPage(@RequestParam(value = "id", defaultValue = "1") long id){
        Survey survey = surveyRepository.findById(id);
        return survey;
    }
}
