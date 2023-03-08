package com.esfandsoft.sysc4806project.controllers;


import com.esfandsoft.sysc4806project.entities.*;
import com.esfandsoft.sysc4806project.repositories.QuestionRepository;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


/**
 * Controller to facilitate REST control of survey creation
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
     * @param payload
     * @throws Exception
     */
    @PostMapping(value = "/process")
    public Survey processNewSurvey(@RequestBody Survey payload, HttpSession httpSession) throws Exception{
        surveyRepository.save(payload); //save repository
        String userName = (String)httpSession.getAttribute("username");
        Optional<User> user = userRepository.findByUsernameIgnoreCase(userName);
        if (user.isEmpty()){
            throw new Exception("NOT LOGGED IN");
        }
        User u = user.get();
        u.addUserSurvey(payload);
        payload.printQuestions();

        userRepository.save(u);

        return payload;
    }
}
