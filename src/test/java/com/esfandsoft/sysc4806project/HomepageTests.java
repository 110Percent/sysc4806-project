package com.esfandsoft.sysc4806project;


import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomepageTests {
    private static final String TEST_USERNAME = "TEST_USERNAME";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        User testUser = new User();
        testUser.setUsername(TEST_USERNAME);
        userRepository.save(testUser);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    /**
     * Should load the landing page when the user is logged in.
     *
     * @throws Exception Generic exception
     */
    @Test
    public void landingPageLoads() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("landing"));
    }

    /**
     * Should load the dashboard page when the user is logged in.
     *
     * @throws Exception Generic exception
     */
    @Test
    public void dashboardLoadsWhenLoggedIn() throws Exception {
        MockHttpSession loggedInSession = new MockHttpSession();
        loggedInSession.setAttribute("username", TEST_USERNAME);

        mockMvc.perform(get("/").session(loggedInSession))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(content().string(containsString(TEST_USERNAME)));
    }
}
