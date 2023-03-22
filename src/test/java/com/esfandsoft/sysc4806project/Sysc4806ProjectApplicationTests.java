package com.esfandsoft.sysc4806project;

import com.esfandsoft.sysc4806project.controllers.*;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Sysc4806ProjectApplicationTests {

    @Autowired
    HomepageController homepageController;

    @Autowired
    ResponseController responseController;

    @Autowired
    LoginController loginController;

    @Autowired
    ResultsController resultsController;

    @Autowired
    SignupController signupController;

    @Autowired
    SurveyCreationController surveyCreationController;

    @Autowired
    SurveyRESTController surveyRESTController;

    @Autowired
    ErrorController errorController;

    @Autowired
    CloseSurveyController closeSurveyController;

    @Autowired
    LogoutController logoutController;

    @Autowired
    ResultsRESTController resultsRESTController;

    @Autowired
    SurveyCreationRESTController surveyCreationRESTController;

    @Autowired
    private SurveyRepository surveyRepository;


    @Test
    void contextLoads() {
        assertThat(homepageController).isNotNull();
        assertThat(loginController).isNotNull();
        assertThat(resultsController).isNotNull();
        assertThat(signupController).isNotNull();
        assertThat(surveyRepository).isNotNull();
        assertThat(surveyRESTController).isNotNull();
        assertThat(responseController).isNotNull();
        assertThat(surveyCreationRESTController).isNotNull();
        assertThat(errorController).isNotNull();
        assertThat(closeSurveyController).isNotNull();
        assertThat(logoutController).isNotNull();
        assertThat(resultsRESTController).isNotNull();
        assertThat(surveyCreationController).isNotNull();
    }

}
