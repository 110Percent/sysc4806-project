package com.esfandsoft.sysc4806project.controllers;


import com.esfandsoft.sysc4806project.entities.Survey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/survey")
public class ResponseController {

    @GetMapping("")
    public String surveyGET(@RequestParam(value = "id", defaultValue = "1") long id){
        return "survey";
    }


}
