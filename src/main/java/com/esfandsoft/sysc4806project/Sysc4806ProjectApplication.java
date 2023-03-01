package com.esfandsoft.sysc4806project;

import com.esfandsoft.sysc4806project.entities.*;
import com.esfandsoft.sysc4806project.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

            // Generate Responses
            AbstractResponse response1 = new WrittenResponse("Answer 1");
            AbstractResponse response2 = new WrittenResponse("Answer 2");
            AbstractResponse response3 = new WrittenResponse("");
            AbstractResponse response4 = new MultiSelectResponse(0);
            AbstractResponse response5 = new MultiSelectResponse(1);
            AbstractResponse response6 = new MultiSelectResponse(2);
            AbstractResponse response7 = new NumericResponse(0);
            AbstractResponse response8 = new NumericResponse(10);
            AbstractResponse response9 = new NumericResponse(20);
            AbstractResponse response10 = new WrittenResponse();
            AbstractResponse response11 = new MultiSelectResponse();
            AbstractResponse response12 = new NumericResponse();
            AbstractResponse response13 = new WrittenResponse();
            AbstractResponse response14 = new WrittenResponse();
            AbstractResponse response15 = new WrittenResponse();
            AbstractResponse response16 = new WrittenResponse();
            AbstractResponse response17 = new WrittenResponse();
            AbstractResponse response18 = new WrittenResponse();

            // Add Responses to Questions
            question1.addQuestionResponse(response1);
            question1.addQuestionResponse(response2);
            question1.addQuestionResponse(response3);
            question2.addQuestionResponse(response4);
            question2.addQuestionResponse(response5);
            question2.addQuestionResponse(response6);
            question3.addQuestionResponse(response7);
            question3.addQuestionResponse(response8);
            question3.addQuestionResponse(response9);
            question4.addQuestionResponse(response10);
            question5.addQuestionResponse(response11);
            question6.addQuestionResponse(response12);
            question7.addQuestionResponse(response13);
            question8.addQuestionResponse(response14);
            question9.addQuestionResponse(response15);
            question10.addQuestionResponse(response16);
            question11.addQuestionResponse(response17);
            question12.addQuestionResponse(response18);

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

            // Print Output
            user1.printSurveys();
            user2.printSurveys();

        };
    }

}
