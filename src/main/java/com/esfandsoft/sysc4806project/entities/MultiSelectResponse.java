package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity representing a Response to a Multi-Select Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
public class MultiSelectResponse extends AbstractResponse {

    private int optionIndex;

    public MultiSelectResponse() {
        this(0);
    }

    public MultiSelectResponse(Integer responseBody) {
        super(QuestionType.MULTISELECT);
        this.optionIndex = responseBody;
    }

    @Override
    public Object getResponseBody() {
        return optionIndex;
    }

    @Override
    void setResponseBody(Object responseBody) {
        if (responseBody instanceof Integer) {
            this.optionIndex = (Integer) responseBody;
        } else {
            // TODO: Switch to using a logger
            System.out.println("Error setting response: " + responseBody);
        }
    }
}
