package com.esfandsoft.sysc4806project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJdbcHttpSession
public class Sysc4806ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sysc4806ProjectApplication.class, args);
    }

}
