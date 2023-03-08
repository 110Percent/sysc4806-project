package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.entities.MultiSelectQuestion;
import com.esfandsoft.sysc4806project.entities.NumericQuestion;
import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.entities.WrittenQuestion;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

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
    public void processNewSurvey(@RequestBody Map<String, Object> payload) throws Exception{
        Survey survey = new Survey((String)payload.get("name")); //get survey title from json and instantiate survey object
        Collection<Object> questions = (Collection<Object>)payload.get("surveyQuestions"); //convert questions to map of strings and objects
        for(Object o: questions){
            Map<String, Object> q = (Map<String, Object>) o;
            switch((String)q.get("responseType")){
                case("WRITTEN"): //process written type questions
                    WrittenQuestion wq = new WrittenQuestion((String)q.get("query"));
                    survey.addSurveyQuestion(wq);
                case("MULTISELECT"): //process multiselect type questions
                    Collection<String> answers = (Collection<String>)q.get("answers");
                    String[] possibleAnswers = answers.toArray(new String[0]);
                    MultiSelectQuestion msq = new MultiSelectQuestion((String)q.get("query"), possibleAnswers);
                    survey.addSurveyQuestion(msq);
                case("NUMERIC"): //process numeric type questions
                    String minStr = (String)q.get("min");
                    String maxStr = (String)q.get("max");
                    int min = Integer.parseInt(minStr);
                    int max = Integer.parseInt(maxStr);
                    NumericQuestion n = new NumericQuestion((String)q.get("query"),min,max);
                    survey.addSurveyQuestion(n);
            }
        }
        surveyRepository.save(survey); //save repository
    }
}
