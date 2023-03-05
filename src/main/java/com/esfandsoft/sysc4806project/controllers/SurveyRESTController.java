package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.repositories.QuestionRepository;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.esfandsoft.sysc4806project.entities.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/survey")
public class SurveyRESTController {

    @Autowired
    SurveyRepository surveyRepository;


    @GetMapping("/noresponses")
    public Survey surveyNoResponses(@RequestParam(value = "id", defaultValue = "1") long id){
        Survey survey = surveyRepository.findById(id);
        survey.clearQuestionResponses();
        return survey;
    }

    @GetMapping("")
    public Survey surveyGET(@RequestParam(value = "id", defaultValue = "1") long id){
        Survey survey = surveyRepository.findById(id);
        return survey;
    }

    @PostMapping("/respond/{id}")
    public void surveyRespond(@RequestBody ArrayList<AbstractResponse> responses, @PathVariable Long id){
        Optional<Survey> survey = surveyRepository.findById(id);
        if(!survey.isPresent())
            return;
        int i = 0;
        for(AbstractQuestion q: survey.get().getSurveyQuestions()){
            q.addQuestionResponse(responses.get(i));
            i++;
        }
        surveyRepository.save(survey.get());
    }

    @GetMapping("/responses")
    public ArrayList<AbstractResponse> responses(@RequestParam(value = "id", defaultValue = "1") long id){
        Survey survey = surveyRepository.findById(id);
        ArrayList<AbstractResponse> responses = new ArrayList<>();
        for(AbstractQuestion q : survey.getSurveyQuestions()){
            responses.addAll(q.getResponses());
        }
        return responses;
    }
}
