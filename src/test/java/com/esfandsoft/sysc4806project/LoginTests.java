package com.esfandsoft.sysc4806project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
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

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
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
        loggedInSession.setAttribute("username", "user1");
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
                        .param("username", "user1")
                        .param("password", "Password1"))
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
                        .param("username", "user1")
                        .param("password", "NOT_Password1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
