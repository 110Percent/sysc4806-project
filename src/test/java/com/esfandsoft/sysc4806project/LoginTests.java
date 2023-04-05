package com.esfandsoft.sysc4806project;

import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test suite for the login controller.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTests {
    private static final String USERNAME = "TEST_USER";
    private static final String PASSWORD = "TEST_PASSWORD";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        userRepository.save(new User(USERNAME,
                new BCryptPasswordEncoder().encode(PASSWORD)));
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    /**
     * Login page should load by default.
     *
     * @throws Exception Generic exception.
     */
    @Test
    public void loginPageShouldLoad() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /**
     * Login page should redirect to the home page if the user is logged in.
     *
     * @throws Exception Generic exception.
     */
    @Test
    public void loginPageShouldntLoadIfLoggedIn() throws Exception {
        MockHttpSession loggedInSession = new MockHttpSession();
        loggedInSession.setAttribute("username", USERNAME);
        mockMvc.perform(get("/login").session(loggedInSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    /**
     * Login page should redirect to the home page on successful login.
     *
     * @throws Exception Generic exception.
     */
    @Test
    public void loginWithValidCredentialsShouldSucceed() throws Exception {
        mockMvc.perform(post("/login").contentType(
                                MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", USERNAME)
                        .param("password", PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    /**
     * Login page should redirect back to login page on unsuccessful login.
     *
     * @throws Exception Generic exception.
     */
    @Test
    public void loginWithInvalidCredentialsShouldFail() throws Exception {
        mockMvc.perform(post("/login").contentType(
                                MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", USERNAME)
                        .param("password", "NOT_VALID_PASSWORD"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    /**
     * Login page should reject signup if username or password is empty.
     *
     * @throws Exception Generic exception
     */
    @Test
    public void loginShouldRejectOnEmptyField() throws Exception {
        // Should fail on empty username
        mockMvc.perform(post("/login").contentType(
                                MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "")
                        .param("password", "TEST_PASSWORD"))
                .andExpect(status().is4xxClientError());

        // Should fail on empty password
        mockMvc.perform(post("/login").contentType(
                                MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "TEST_USER")
                        .param("password", ""))
                .andExpect(status().is4xxClientError());
    }
}
