package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Abstract Entity Representing a question response
 *
 * @author Ethan Houlahan 101145675
 */
@Entity
public abstract class AbstractResponse {

    @Id
    @GeneratedValue
    long id;
    private QuestionType responseType;
    private Object responseBody;

    protected AbstractResponse() {
        this(QuestionType.MULTISELECT, null);
    }

    protected AbstractResponse(QuestionType responseType, Object responseBody) {
        this.responseType = responseType;
        this.responseBody = responseBody;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    public QuestionType getResponseType() {
        return responseType;
    }

    public void setResponseType(QuestionType responseType) {
        this.responseType = responseType;
    }
}
