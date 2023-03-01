package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;

/**
 * Entity representing a Response to a Numeric Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
public class NumericResponse extends AbstractResponse {

    private Integer response;

    public NumericResponse() {
        this(404);
    }

    public NumericResponse(Integer responseBody) {
        super(QuestionType.NUMERIC);
        this.response = responseBody;
    }

    @Override
    Object getResponseBody() {
        return response;
    }

    @Override
    void setResponseBody(Object responseBody) {
        if (responseBody instanceof Integer) {
            this.response = (Integer) responseBody;
        } else {
            // TODO: Switch to using a logger
            System.out.println("Error setting response: " + responseBody);
        }
    }

}

