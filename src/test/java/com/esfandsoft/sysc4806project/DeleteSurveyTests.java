package com.esfandsoft.sysc4806project;

import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteSurveyTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;


    @Test
    public void surveyDeletesProperly() throws Exception {
        User user = new User("TEST_USER", new BCryptPasswordEncoder().encode("Password1"));
        Survey survey = new Survey("TEST_SURVEY");
        survey.setIsClosed(true);
        user.addUserSurvey(survey);
        userRepository.save(user);
        long id = survey.getId();

        MockHttpSession loggedInSession = new MockHttpSession();
        loggedInSession.setAttribute("username", "TEST_USER");
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        mockMvc.perform(get("/survey/delete?id=" + id).session(loggedInSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(not(containsString("TEST_SURVEY"))));

        userRepository.delete(user);
    }

    @Test
    public void deleteSurveyNotAccessibleByOtherUsers() throws Exception {
        User user = new User("TEST_USER", new BCryptPasswordEncoder().encode("Password1"));
        Survey survey = new Survey("TEST_SURVEY");
        survey.setIsClosed(true);
        user.addUserSurvey(survey);
        userRepository.save(user);
        long id = survey.getId();

        MockHttpSession loggedInSession = new MockHttpSession();
        loggedInSession.setAttribute("username", "CURIOUS_USER");
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        mockMvc.perform(get("/survey/delete?id=" + id).session(loggedInSession))
                .andExpect(status().is3xxRedirection());

        loggedInSession.setAttribute("username", "TEST_USER");
        mockMvc.perform(get("/").session(loggedInSession))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TEST_SURVEY")));

        userRepository.delete(user);
    }

}
