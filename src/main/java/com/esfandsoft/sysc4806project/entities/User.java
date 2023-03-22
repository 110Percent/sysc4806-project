package com.esfandsoft.sysc4806project.entities;

import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    private static final Logger logger = LogManager.getLogger(WrittenResponse.class);
    @Id
    @GeneratedValue
    long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    Collection<Survey> surveys;
    private String username;
    private String passwordHash;


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
     * Get a list of all the closed surveys
     *
     * @return List - list of all closed surveys
     */
    public List<Survey> acquireClosedSurveys() {
        List<Survey> closedSurveys = new ArrayList<Survey>();
        for (Survey s : this.surveys) {
            if (s.getIsClosed()) {
                closedSurveys.add(s);
            }
        }
        return closedSurveys;
    }

    /**
     * Print contents of all user surveys
     */
    public void printSurveys() {
        for (Survey s : this.surveys) {
            logger.info("Survey #" + s.getId() + ": " + s.getSurveyTitle());
            s.printQuestions();
        }
    }

    /**
     * Removes a survey from the User
     *
     * @param survey the Survey to remove
     * @author Nicholas Sendyk, 101143602
     */
    public void removeSurvey(Survey survey) {
        this.surveys.remove(survey);
    }
}
