package com.esfandsoft.sysc4806project;

import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndpointTests {
    @Value(value = "${local.server.port}")
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private String getForObject(String path) {
        return restTemplate.getForObject("http://localhost:" + port + path, String.class);
    }

    @Test
    public void rootShowsLandingPage() {
        assertThat(getForObject("/")).contains("landing-title");
    }

    @Test
    public void loginPageShows() {
        assertThat(getForObject("/login")).contains("<h1>Log In</h1>");
    }

    @Test
    public void signupPageShows() {
        assertThat(getForObject("/signup")).contains("<h1>Sign Up</h1>");
    }

    @Test
    public void surveyCreationPageShowsSignup() {
        assertThat(getForObject("/surveyCreation")).contains("<h1>Sign Up</h1>");
    }

    @Test
    public void errorPageShows() {
        assertThat(getForObject("/error")).contains("<h1 id=\"error_h1\">Error 404 - Page Not Found</h1>");
    }

    @Test
    public void surveyResultsPageRedirectsWhenLoggedOut() throws Exception {
        MockHttpSession loggedOutSession = new MockHttpSession();
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        User user = new User("TEST_USER", new BCryptPasswordEncoder().encode("Password1"));
        userRepository.save(user);

        mockMvc.perform(get("/results/123").session(loggedOutSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        userRepository.delete(user);
    }

    @Test
    public void surveyResultsPageHasTitle() throws Exception {
        MockHttpSession loggedInSession = new MockHttpSession();
        loggedInSession.setAttribute("username", "TEST_USER");
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();


        User user = new User("TEST_USER", new BCryptPasswordEncoder().encode("Password1"));
        Survey survey = new Survey("TEST_SURVEY");
        survey.setIsClosed(true);
        user.addUserSurvey(survey);
        userRepository.save(user);
        long id = survey.getId();

        mockMvc.perform(get("/results/" + id).session(loggedInSession))
                .andExpect(status().isOk())
                .andExpect(view().name("survey_results"))
                .andExpect(content().string(containsString("Results Example")));

        userRepository.delete(user);
    }

    @Test
    public void surveyResultsPageRedirectsWhenSurveyDoesNotExist() throws Exception {
        MockHttpSession loggedInSession = new MockHttpSession();
        loggedInSession.setAttribute("username", "TEST_USER");
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();


        User user = new User("TEST_USER", new BCryptPasswordEncoder().encode("Password1"));
        userRepository.save(user);

        mockMvc.perform(get("/results/123").session(loggedInSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/error"));
        userRepository.delete(user);
    }
}
