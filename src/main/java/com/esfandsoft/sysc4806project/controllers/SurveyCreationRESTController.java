package com.esfandsoft.sysc4806project.controllers;


import com.esfandsoft.sysc4806project.entities.*;
import com.esfandsoft.sysc4806project.enums.QuestionType;
import com.esfandsoft.sysc4806project.repositories.QuestionRepository;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


/**
 * Controller to facilitate REST control of survey creation
 *
 * @author Ethan Houlahan, 101145675
 */
@RestController
@RequestMapping("/createsurvey")
public class SurveyCreationRESTController {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Process user created survey
     *
     * @param payload
     * @throws Exception
     */
    @PostMapping(value = "/process")
    public Survey processNewSurvey(@RequestBody Survey payload, HttpSession httpSession) throws Exception {
        String userName = (String) httpSession.getAttribute("username");
        Optional<User> user = userRepository.findByUsernameIgnoreCase(userName); //get current user
        if (user.isEmpty()) {
            throw new Exception("NOT LOGGED IN"); //if not logged somehow return exception
        }
        int j = 0; // get position of question
        for (AbstractQuestion aq: payload.getSurveyQuestions()){ //iterate through all questions in survey
            if (aq.getQuery().equals("")){
                aq.setQuery("Question " + j); //If null, set question query to "Question {integer of location}"
            }

            if(aq.getQuestionType().equals(QuestionType.MULTISELECT)){ //if question multiselect
                MultiSelectQuestion mq = (MultiSelectQuestion) aq; //instantiate question as multiselect question
                String[] potentialAnswers = mq.getPotentialAnswers(); // get answers as an array
                for(int i = 0; i < potentialAnswers.length; i++){ //iterate through list
                    if (potentialAnswers[i].equals("")){ //if question null
                        potentialAnswers[i] = Character.toString((char)(65+i)); //change to A,B,C ..., etc if question null
                    }
                }
                MultiSelectQuestion tempMq = new MultiSelectQuestion(aq.getQuery(), potentialAnswers);  // make new multiselect question with new potential answer list void of null strings
                aq = (AbstractQuestion) tempMq; //set abstract question equal to new multiselect question
            }
            if(aq.getQuestionType().equals(QuestionType.NUMERIC)){ //select only numeric questions
                NumericQuestion nq = (NumericQuestion)aq; //Get numeric question interpretation of abstract question type
                if(nq.getMax() <= nq.getMin()){ //if max val less than min
                    NumericQuestion tempNq = new NumericQuestion(aq.getQuery(),nq.getMax() - 1, nq.getMax()); //create new temporary numeric question with proper max and min values
                    aq = (AbstractQuestion)tempNq; //assign value to abstract question from survey object
                }
            }
            j++;
        }
        User u = user.get(); //get user as instantiated user class object

        if (payload.getSurveyTitle().equals("")){
            payload.setSurveyTitle(userName + "'s Survey #" + (u.getSurveys().size() + 1)); //Set survey title to user's survey title with number
        }

        surveyRepository.save(payload); //save repository


        u.addUserSurvey(payload); //assign survey to user

        userRepository.save(u); //save user associated with survey
        return payload; //return payload
    }
}
