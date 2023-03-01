package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;

/**
 * Entity representing a Response to a Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
public class WrittenResponse extends AbstractResponse{

    private Object responseBody;

    private String response;

    public WrittenResponse() {
        this(QuestionType.WRITTEN, null);
    }

    public WrittenResponse(QuestionType responseType, Object responseBody) {
        super(responseType, responseBody);
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    public QuestionType getResponseType() {
        return super.getResponseType();
    }

    public void setResponseType(QuestionType responseType) {
        super.setResponseType(responseType);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
