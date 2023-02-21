package com.esfandsoft.sysc4806project;

import com.esfandsoft.sysc4806project.entities.Question;
import com.esfandsoft.sysc4806project.entities.Survey;
import com.esfandsoft.sysc4806project.entities.User;
import com.esfandsoft.sysc4806project.enums.QuestionType;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Sysc4806ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sysc4806ProjectApplication.class, args);
    }

    /**
     * Creates a demo version of the system
     *
     * NOTE: Comment out @Bean below to remove the functionality
     *
     * @author Nicholas Sendyk, 101143602
     * @param repository the system repository
     * @return CommandLineRunner
     */
    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {

            Logger log = LogManager.getLogger(Sysc4806ProjectApplication.class);

            // Generate passwords
            String passwordHash1 = new BCryptPasswordEncoder().encode("Password1");
            String passwordHash2 = new BCryptPasswordEncoder().encode("Password2");

            // Generate Users
            User user1 = new User("User 1", passwordHash1);
            User user2 = new User("User 2", passwordHash2);

            // Generate Surveys
            Survey survey1 = new Survey("Survey 1");
            Survey survey2 = new Survey("Survey 2");
            Survey survey3 = new Survey("Survey 3");
            Survey survey4 = new Survey("Survey 4");

            // Generate Questions
            // TODO: Implement Questions Interface
            Question question1 = new Question("Who?", QuestionType.WRITTEN, new String[]{});
            Question question2 = new Question("What?", QuestionType.MULTISELECT, new String[]{});
            Question question3 = new Question("When?", QuestionType.NUMERIC, new String[]{});

            // Generate Responses
            // TODO: Implement Responses Interface

            // TODO: Continue implementing these
            // Add Responses to Questions

            // Add Questions to Surveys

            // Add surveys to users

            // Save Users to Repository
            repository.save(user1);
            repository.save(user2);

        };
    }

}
