package com.esfandsoft.sysc4806project.entities;
import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Entity representing a Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
@Entity
public class Question{

    @Id
    @GeneratedValue
    long id;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    Collection<Response> responses;

    private String query;
    private QuestionType questionType;
    private Object answers;

    /**
     * Default constructor for Question
     */
    public Question() {
        this("Default Question?", QuestionType.MULTISELECT, new String[]{"A", "B", "C", "D"});
    }

    public Question(String query, QuestionType questionType, Object answers) {
        this.responses = new ArrayList<Response>();
        this.query = query;
        this.questionType = questionType;
        this.answers = answers;
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
     * Adds a singular response to a question
     *
     * @author Nicholas Sendyk, 101143602
     * @param response the Question to add to the Survey
     */
    public void addQuestionResponse(Response response){
        responses.add(response);
    }

    /**
     * Adds an ArrayList of Responses to the Question
     *
     * @author Nicholas Sendyk, 101143602
     * @param responses list of responses to add
     */
    public void addListQuestionResponses(ArrayList<Response> responses) {
        for (Response r: responses) {
            addQuestionResponse(r);
        }
    }
}
