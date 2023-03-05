package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.*;

/**
 * Abstract Entity Representing a question response
 *
 * @author Ethan Houlahan 101145675
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractResponse {

    @Id
    @GeneratedValue
    long id;
    private QuestionType responseType;

    protected AbstractResponse() {
        this(QuestionType.MULTISELECT);
    }

    protected AbstractResponse(QuestionType responseType) {
        this.responseType = responseType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    abstract Object getResponseBody();

    abstract void setResponseBody(Object responseBody);

    public QuestionType getResponseType() {
        return responseType;
    }

    public void setResponseType(QuestionType responseType) {
        this.responseType = responseType;
    }
}
