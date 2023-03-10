package com.esfandsoft.sysc4806project.controllers;


import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.entities.User;
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
        surveyRepository.save(payload); //save repository
        String userName = (String) httpSession.getAttribute("username");
        Optional<User> user = userRepository.findByUsernameIgnoreCase(userName); //get current user
        if (user.isEmpty()) {
            throw new Exception("NOT LOGGED IN"); //if not logged somehow return exception
        }
        User u = user.get(); //get user as instantiated user class object
        u.addUserSurvey(payload); //assign survey to user

        userRepository.save(u); //save user associated with survey
        return payload; //return payload
    }
}
