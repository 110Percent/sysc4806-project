package com.esfandsoft.sysc4806project.controllers;

import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/survey")
public class CloseSurveyController {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/close")
    public RedirectView closeSurvey(@RequestParam(value = "id") long id, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return new RedirectView("/");
        }

        Optional<User> fetchedUser = userRepository.findByUsername(String.valueOf(session.getAttribute("username")));
        if (fetchedUser.isEmpty()) {
            return new RedirectView("/");
        }

        Collection<Survey> surveys = fetchedUser.get().getSurveys();
        for(Survey s : surveys){
            if(s.getId() == id){
                s.setIsClosed(true);
                surveyRepository.save(s);
                return new RedirectView("/");
            }
        }

        return new RedirectView("/");
    }
}
