package com.esfandsoft.sysc4806project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class EndpointTests {
    @Value(value = "${local.server.port}")
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    private String getForObject(String path) {
        return restTemplate.getForObject("http://localhost:" + port + path, String.class);
    }

    @Test
    public void rootShowsLandingPage() {
        assertThat(getForObject("/")).contains("<h2>Hello</h2>");
    }

    @Test
    public void loginPageShows() {
        assertThat(getForObject("/login")).contains("<h1>Log In</h1>");
    }

    @Test
    public void signupPageshows() {
        assertThat(getForObject("/signup")).contains("<h1>Sign Up</h1>");
    }

    @Test
    public void surveyCreationPageShowsSignup() {
        assertThat(getForObject("/surveyCreation")).contains("<h1>Sign Up</h1>");
    }
}
