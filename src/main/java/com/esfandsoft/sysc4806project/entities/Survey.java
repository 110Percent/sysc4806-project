package com.esfandsoft.sysc4806project.entities;

import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Logger logger = LogManager.getLogger(Survey.class);
    @Id
    @GeneratedValue
    long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    Collection<AbstractQuestion> surveyQuestions;
    private String surveyTitle;
    private boolean isClosed;

    /**
     * Default constructor for Survey
     */
    public Survey() {
        this("Default Survey");
    }

    public Survey(String surveyTitle) {
        this.surveyTitle = surveyTitle;
        this.surveyQuestions = new ArrayList<AbstractQuestion>();
        this.isClosed = false;
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

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean closed) {
        // Generate results
        for (AbstractQuestion q: this.surveyQuestions) {
            q.initResultsGeneration();
        }
        isClosed = closed;
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

    public void clearQuestionResponses(){
        for(AbstractQuestion q : this.surveyQuestions){
            q.clearResponses();
        }
    }

    /**
     * Print all the questions in a survey
     */
    public void printQuestions() {
        for (AbstractQuestion q : this.surveyQuestions) {
            logger.info("Question #" + q.getId() + ": " + q.getQuery());
            q.printResponses();
        }
    }
}
