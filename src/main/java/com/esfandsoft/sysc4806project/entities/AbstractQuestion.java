package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract Entity representing a Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
@Entity
public abstract class AbstractQuestion {

    @Id
    @GeneratedValue
    long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    Collection<AbstractResponse> responses;

    private String query;
    private QuestionType questionType;
    private static Logger logger = LogManager.getLogger(AbstractQuestion.class);

    protected AbstractQuestion() {
        this(QuestionType.MULTISELECT, "Default Question?");
    }

    protected AbstractQuestion(QuestionType questionType, String query) {
        this.questionType = questionType;
        this.query = query;
        this.responses = new ArrayList<>();
    }

    abstract Object getAnswers();

    abstract void setAnswers(Object answers);

    // TODO:
    //  abstract Object generateResults();

    /**
     * Adds a singular response to a question
     *
     * @param response the Question to add to the Survey
     * @author Nicholas Sendyk, 101143602
     */
    public void addQuestionResponse(AbstractResponse response) {
        if (response.getResponseType() == this.getQuestionType()) {
            this.responses.add(response);
        } else {
            logger.info("Error adding response: " + response);
        }
    }

    /**
     * Adds an ArrayList of Responses to the Question
     *
     * @param responses list of responses to add
     * @author Nicholas Sendyk, 101143602
     */
    public void addListQuestionResponses(ArrayList<AbstractResponse> responses) {
        for (AbstractResponse r : responses) {
            addQuestionResponse(r);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<AbstractResponse> getResponses() {
        return responses;
    }

    public void setResponses(Collection<AbstractResponse> responses) {
        this.responses = responses;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public void printResponses() {
        for (AbstractResponse r: this.responses) {
            logger.info("Response #" + r.getId() +": " + r.getResponseBody());
        }
    }
}
