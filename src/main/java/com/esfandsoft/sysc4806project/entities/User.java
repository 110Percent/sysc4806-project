package com.esfandsoft.sysc4806project.entities;

import jakarta.persistence.*;

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

    String username;

    String passwordHash;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    Collection<Survey> surveys;

    /**
     * Default constructor for User
     */
    public User() {
        this("", "");
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
     * @author Nicholas Sendyk, 101143602
     * @param survey the Survey to add to the User
     */
    public void addUserSurvey(Survey survey){
        surveys.add(survey);
    }

    /**
     * Adds an ArrayList of Surveys to the User
     *
     * @author Nicholas Sendyk, 101143602
     * @param surveys list of surveys to add
     */
    public void addListUserSurveys(ArrayList<Survey> surveys) {
        for (Survey s: surveys) {
            addUserSurvey(s);
        }
    }

}
