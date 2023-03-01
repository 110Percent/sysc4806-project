package com.esfandsoft.sysc4806project.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Entity representing a Survey.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
@Entity
public class Survey {

    private final String surveyTitle;
    @Id
    @GeneratedValue
    long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    Collection<AbstractQuestion> surveyQuestions;

    /**
     * Default constructor for Survey
     */
    public Survey() {
        this("Default Survey");
    }

    public Survey(String surveyTitle) {
        this.surveyTitle = surveyTitle;
        surveyQuestions = new ArrayList<AbstractQuestion>();
    }

    public Collection<AbstractQuestion> getSurveyQuestions() {
        return surveyQuestions;
    }

    public void setSurveyQuestions(Collection<AbstractQuestion> surveyQuestions) {
        this.surveyQuestions = surveyQuestions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Adds a singular question to a survey
     *
     * @param question the Question to add to the Survey
     * @author Nicholas Sendyk, 101143602
     */
    public void addSurveyQuestion(AbstractQuestion question) {
        surveyQuestions.add(question);
    }

    /**
     * Adds an ArrayList of Questions to the surveyQuestions
     *
     * @param questions list of questions to add
     * @author Nicholas Sendyk, 101143602
     */
    public void addListSurveyQuestions(ArrayList<AbstractQuestion> questions) {
        for (AbstractQuestion q : questions) {
            addSurveyQuestion(q);
        }
    }
}
