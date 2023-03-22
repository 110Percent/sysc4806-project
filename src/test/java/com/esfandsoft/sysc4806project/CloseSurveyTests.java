package com.esfandsoft.sysc4806project;

import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.repositories.SurveyRepository;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CloseSurveyTests {
    private static final String TEST_USERNAME = "TEST_USERNAME";
    private MockMvc mockMvc;
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        User testUser = new User();
        Survey survey = new Survey("survey");
        survey.setIsClosed(false);
        testUser.addUserSurvey(survey);
        testUser.setUsername(TEST_USERNAME);
        userRepository.save(testUser);

    }

    @Test
    public void setCloseLoggedIn() throws Exception {
        MockHttpSession loggedInSession = new MockHttpSession();
        loggedInSession.setAttribute("username", TEST_USERNAME);

        mockMvc.perform(get("/survey/close?id=1").session(loggedInSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertTrue(surveyRepository.findById(1L).getIsClosed());

    }

    @Test
    public void setCloseNotLoggedIn() throws Exception {
        MockHttpSession loggedInSession = new MockHttpSession();

        mockMvc.perform(get("/survey/close?id=1").session(loggedInSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertFalse(surveyRepository.findById(1L).getIsClosed());
    }

    @Test
    public void closeSurveyNotPresent() throws Exception {
        MockHttpSession loggedInSession = new MockHttpSession();
        loggedInSession.setAttribute("username", TEST_USERNAME);

        mockMvc.perform(get("/survey/close?id=2").session(loggedInSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

    }

    @Test
    public void closeSurveyOfOtherUser() throws Exception {
        User user2 = new User();
        Survey survey2 = new Survey("survey");
        survey2.setIsClosed(false);
        user2.addUserSurvey(survey2);
        userRepository.save(user2);

        MockHttpSession loggedInSession = new MockHttpSession();
        loggedInSession.setAttribute("username", TEST_USERNAME);

        mockMvc.perform(get("/survey/close?id=2").session(loggedInSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertFalse(surveyRepository.findById(2L).getIsClosed());
    }
}
