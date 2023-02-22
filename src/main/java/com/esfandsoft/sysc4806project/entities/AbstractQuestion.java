package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.*;

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
    Collection<Response> responses;

    private String query;
    private QuestionType questionType;

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

    /**
     * Adds a singular response to a question
     *
     * @param response the Question to add to the Survey
     * @author Nicholas Sendyk, 101143602
     */
    public void addQuestionResponse(Response response) {
        if (response.getResponseType() == this.getQuestionType()) {
            this.responses.add(response);
        } else {
            // TODO: Switch to using a logger
            System.out.println("Error adding response: " + response);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<Response> getResponses() {
        return responses;
    }

    public void setResponses(Collection<Response> responses) {
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

    /**
     * Adds an ArrayList of Responses to the Question
     *
     * @param responses list of responses to add
     * @author Nicholas Sendyk, 101143602
     */
    public void addListQuestionResponses(ArrayList<Response> responses) {
        for (Response r : responses) {
            addQuestionResponse(r);
        }
    }

}
