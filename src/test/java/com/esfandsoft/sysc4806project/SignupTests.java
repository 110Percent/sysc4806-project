package com.esfandsoft.sysc4806project;

import com.esfandsoft.sysc4806project.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    /**
     * Signup page should load by default.
     *
     * @throws Exception Generic exception
     */
    @Test
    public void signupPageShouldLoad() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"));
    }

    @Test
    public void signupShouldAddUser() throws Exception {
        mockMvc.perform(post("/signup").contentType(
                                MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "TEST_USER")
                        .param("password", "TEST_PASSWORD")
                        .param("matchingPassword", "TEST_PASSWORD"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        assertTrue(userRepository.findByUsername("TEST_USER").isPresent());
    }

    /**
     * Signup page should reject signup if passwords do not match.
     *
     * @throws Exception Generic exception
     */
    @Test
    public void signupShouldRejectOnNonMatchingPasswords() throws Exception {
        mockMvc.perform(post("/signup").contentType(
                                MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "TEST_USER")
                        .param("password", "TEST_PASSWORD")
                        .param("matchingPassword", "WRONG_PASSWORD"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signup"));
        assertTrue(userRepository.findByUsername("TEST_USER").isEmpty());
    }

    /**
     * Signup page should reject signup if username or password is empty.
     *
     * @throws Exception Generic exception
     */
    @Test
    public void signupShouldRejectOnEmptyField() throws Exception {
        // Should fail on empty username
        mockMvc.perform(post("/signup").contentType(
                                MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "")
                        .param("password", "TEST_PASSWORD")
                        .param("matchingPassword", "TEST_PASSWORD"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signup"));

        // Should fail on empty password
        mockMvc.perform(post("/signup").contentType(
                                MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "TEST_USER")
                        .param("password", "")
                        .param("matchingPassword", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signup"));
        assertTrue(userRepository.findByUsername("TEST_USER").isEmpty());
    }

    /**
     * Signup page should reject signup if username already exists.
     *
     * @throws Exception Generic exception
     */
    @Test
    public void signupShouldRejectOnExistingUsername() throws Exception {
        // First signup should succeed
        mockMvc.perform(post("/signup").contentType(
                                MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "TEST_USER")
                        .param("password", "TEST_PASSWORD")
                        .param("matchingPassword", "TEST_PASSWORD"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        // Second signup should fail
        mockMvc.perform(post("/signup").contentType(
                                MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "TEST_USER")
                        .param("password", "TEST_PASSWORD")
                        .param("matchingPassword", "TEST_PASSWORD"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signup"));
    }
}
