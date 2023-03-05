package com.esfandsoft.sysc4806project.entities;

import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Entity representing a User.
 *
 * @author Ethan Houlahan, 101145675
 * @author Curtis Davies, 101146353
 * @author Nicholas Sendyk, 101143602
 */
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    long id;

    private String username;

    private String passwordHash;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    Collection<Survey> surveys;

    private static Logger logger = LogManager.getLogger(WrittenResponse.class);


    /**
     * Default constructor for User
     */
    public User() {
        this("Test User", new BCryptPasswordEncoder().encode("default"));
    }

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.surveys = new ArrayList<Survey>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(Collection<Survey> surveys) {
        this.surveys = surveys;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Adds a singular survey to a user
     *
     * @param survey the Survey to add to the User
     * @author Nicholas Sendyk, 101143602
     */
    public void addUserSurvey(Survey survey) {
        surveys.add(survey);
    }

    /**
     * Adds an ArrayList of Surveys to the User
     *
     * @param surveys list of surveys to add
     * @author Nicholas Sendyk, 101143602
     */
    public void addListUserSurveys(ArrayList<Survey> surveys) {
        for (Survey s : surveys) {
            addUserSurvey(s);
        }
    }

    public void printSurveys() {
        for (Survey s: this.surveys) {
            logger.info("Survey #" + s.getId() + ": " + s.getSurveyTitle());
            s.printQuestions();
        }
    }

}
