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

    /**
     * Get the length of the largest set of responses
     *
     * @return Integer - the length of the largest set of responses
     */
    private int getLargestResultsLength() {
        int largestLen = 0;
        for (AbstractQuestion q : this.surveyQuestions) {
            int curLen = q.generateResults().length;
            if (curLen > largestLen) {
                largestLen = curLen;
            }
        }
        return largestLen;
    }

    /**
     * Get the length of the largest set of options for responses
     *
     * @return Integer - the length of the largest set of responses
     */
    private int getLargestOptionsSet() {
        int largestLen = 0;
        for (AbstractQuestion q : this.surveyQuestions) {
            int curLen = q.getAnswers().length;
            if (curLen > largestLen) {
                largestLen = curLen;
            }
        }
        return largestLen;
    }

    /**
     * Get the results of every question in the Survey
     *
     * @return String[][] - the first index is the index of the question, the second is the index of the response
     */
    public String[][] getSurveyResults() {
        String[][] rs = new String[this.surveyQuestions.size()][getLargestResultsLength()];
        int i = 0;
        for (AbstractQuestion q : this.surveyQuestions) {
            rs[i] = q.generateResults();
            i++;
        }
        return rs;
    }

    /**
     * Get a list of all the questions of a Survey
     *
     * @return
     */
    public String[][][] getQueries() {
        String[][][] qs = new String[this.surveyQuestions.size()][2][getLargestOptionsSet()];
        int i = 0;
        for (AbstractQuestion q : this.surveyQuestions) {
            qs[i][1] = new String[]{q.getQuery()};
            qs[i][2] = q.getAnswers();
            i++;
        }
        return qs;
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
