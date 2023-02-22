package com.esfandsoft.sysc4806project;

import com.esfandsoft.sysc4806project.entities.*;
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

import java.util.ArrayList;
import java.util.Arrays;

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
            AbstractQuestion question1 = new WrittenQuestion("Who?");
            AbstractQuestion question2 = new MultiSelectQuestion("What?",
                    new ArrayList<String>(Arrays.asList("Cat", "Dog", "Bear")));
            AbstractQuestion question3 = new NumericQuestion("When?", 0, 20);
            AbstractQuestion question4 = new WrittenQuestion("How?");
            AbstractQuestion question5 = new MultiSelectQuestion("Where?",
                    new ArrayList<String>(Arrays.asList("Home", "School", "Work")));
            AbstractQuestion question6 = new NumericQuestion("Money?", 0, 100);
            AbstractQuestion question7 = new WrittenQuestion("Why?");
            AbstractQuestion question8 = new WrittenQuestion("Why?");
            AbstractQuestion question9 = new WrittenQuestion("Why?");
            AbstractQuestion question10 = new WrittenQuestion("Why?");
            AbstractQuestion question11 = new WrittenQuestion("Why?");
            AbstractQuestion question12 = new WrittenQuestion("Why?");

            // TODO: Implement Responses Interface
            // TODO: Generate Responses

            // TODO: Add Responses to Questions

            // Add Questions to Surveys
            survey1.addSurveyQuestion(question1);
            survey1.addSurveyQuestion(question2);
            survey1.addSurveyQuestion(question3);
            survey2.addSurveyQuestion(question4);
            survey2.addSurveyQuestion(question5);
            survey2.addSurveyQuestion(question6);
            survey3.addSurveyQuestion(question7);
            survey3.addSurveyQuestion(question8);
            survey3.addSurveyQuestion(question9);
            survey4.addSurveyQuestion(question10);
            survey4.addSurveyQuestion(question11);
            survey4.addSurveyQuestion(question12);

            // Add surveys to users
            user1.addUserSurvey(survey1);
            user1.addUserSurvey(survey2);
            user2.addUserSurvey(survey3);
            user2.addUserSurvey(survey4);

            // Save Users to Repository
            repository.save(user1);
            repository.save(user2);

        };
    }

}
