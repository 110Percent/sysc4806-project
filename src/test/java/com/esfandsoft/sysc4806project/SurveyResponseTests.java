package com.esfandsoft.sysc4806project;

import com.esfandsoft.sysc4806project.entities.*;
import com.esfandsoft.sysc4806project.repositories.ResponseRepository;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SurveyResponseTests {

    private MockMvc mockMvc;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void surveyPageLoads() throws Exception{
        mockMvc.perform(get("/survey?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("survey"))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void writtenResponseShouldPost() throws Exception{
        Survey survey = new Survey("Survey");
        WrittenQuestion question = new WrittenQuestion("What?");
        survey.addSurveyQuestion(question);
        surveyRepository.save(survey);
        long id = survey.getId();
        mockMvc.perform(post("/api/survey/respond/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"responseType\": \"WRITTEN\", \"responseBody\": \"yo\"}]"))
                .andExpect(status().isOk());

        assertTrue(responseRepository.findById(1L).getResponseBody().equals("yo"));


    }

    @Test
    public void numericResponseShouldPost() throws Exception{
        Survey survey = new Survey("Survey");
        NumericQuestion question = new NumericQuestion("how many?", 0, 10);
        survey.addSurveyQuestion(question);
        surveyRepository.save(survey);
        long id = survey.getId();
        mockMvc.perform(post("/api/survey/respond/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"responseType\": \"NUMERIC\", \"responseBody\": \"1\"}]"))
                .andExpect(status().isOk());


        assertTrue(responseRepository.findById(1L).getResponseBody().equals(1));


    }

    @Test
    public void multiselectResponseShouldPost() throws Exception{
        Survey survey = new Survey("Survey");
        MultiSelectQuestion question = new MultiSelectQuestion("which?", new String[]{"Cat", "Dog", "Bear"});
        survey.addSurveyQuestion(question);
        surveyRepository.save(survey);
        long id = survey.getId();
        mockMvc.perform(post("/api/survey/respond/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"responseType\": \"MULTISELECT\", \"responseBody\": \"1\"}]"))
                .andExpect(status().isOk());


        assertTrue(responseRepository.findById(1L).getResponseBody().equals(1));


    }


}
