package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.entities.AbstractQuestion;
import com.esfandsoft.sysc4806project.entities.AbstractResponse;
import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/survey")
public class SurveyRESTController {

    @Autowired
    SurveyRepository surveyRepository;

    /**
     * get survey as JSON
     *
     * @param id
     * @return survey
     */
    @GetMapping("")
    public Survey surveyGET(@RequestParam(value = "id", defaultValue = "1") long id) {
        Survey survey = surveyRepository.findById(id);
        System.out.println("================================================");
        System.out.println(
                survey.getSurveyQuestions().size() + " survey questions");
        for (AbstractQuestion q : survey.getSurveyQuestions()) {
            System.out.println(q.getResponses().size() + " responses");
        }
        return survey;
    }

    /**
     * Accept JSON responses and add them to the question responses
     *
     * @param responses
     * @param id
     * @return responses
     */
    @PostMapping("/respond/{id}")
    public ArrayList<AbstractResponse> surveyRespond(@RequestBody ArrayList<AbstractResponse> responses, @PathVariable Long id) {
        Optional<Survey> optSurvey = surveyRepository.findById(id);
        if (!optSurvey.isPresent())
            return null;
        int i = 0;
        Survey survey = optSurvey.get();
        for (AbstractQuestion q : survey.getSurveyQuestions()) {
            q.addQuestionResponse(responses.get(i));
            i++;
        }
        surveyRepository.save(survey);

        return responses;
    }
}
