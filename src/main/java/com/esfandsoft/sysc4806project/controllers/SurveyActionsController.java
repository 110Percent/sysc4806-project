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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/survey")
public class SurveyActionsController {

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
        for (Survey s : surveys) {
            if (s.getId() == id) {
                s.setIsClosed(true);
                surveyRepository.save(s);
                return new RedirectView("/");
            }
        }

        return new RedirectView("/");
    }

    @GetMapping("/delete")
    public RedirectView deleteSurvey(@RequestParam(value = "id") long id, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return new RedirectView("/");
        }

        Optional<User> fetchedUser = userRepository.findByUsername(String.valueOf(session.getAttribute("username")));
        if (fetchedUser.isEmpty()) {
            return new RedirectView("/");
        }

        // Get the users surveys from the database
        Collection<Survey> s = fetchedUser.get().getSurveys();
        ArrayList<Long> s_ids = new ArrayList<>();
        for (Survey s1 : s) {
            s_ids.add(s1.getId());
        }

        // if the user is the owner of the survey
        if (s_ids.contains(id)) {
            // remove the survey from the user
            Survey fetchedSurvey = surveyRepository.findById(id);
            fetchedSurvey.removeAllQuestions();
            fetchedUser.get().removeSurvey(fetchedSurvey);
        }

        return new RedirectView("/");
    }
}
