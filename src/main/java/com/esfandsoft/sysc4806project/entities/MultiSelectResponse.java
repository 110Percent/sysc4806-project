package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity representing a Response to a Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
public class MultiSelectResponse extends AbstractResponse{

    private Object responseBody;

    private int optionIndex;

    public MultiSelectResponse() {
        this(QuestionType.MULTISELECT, null);
    }

    public MultiSelectResponse(QuestionType responseType, Object responseBody) {
        super(responseType, responseBody);
    }

    public int getOptionIndex() {
        return optionIndex;
    }

    public void setOptionIndex(int optionIndex) {
        this.optionIndex = optionIndex;
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
