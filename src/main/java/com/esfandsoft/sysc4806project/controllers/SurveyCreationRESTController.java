package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.entities.AbstractQuestion;
import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller to facilitate REST control of survey creation
 * @author Ethan Houlahan, 101145675
 */
@RestController
@RequestMapping("/createsurvey")
public class SurveyCreationRESTController {

    @Autowired
    SurveyRepository surveyRepository;

    /**
     * Process user created survey
     * @param payload
     * @throws Exception
     */
    @PostMapping(value = "/process")
    public Survey processNewSurvey(@RequestBody Survey payload) throws Exception{
        surveyRepository.save(payload); //save repository
        return payload;
    }
}
