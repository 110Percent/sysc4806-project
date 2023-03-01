package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;

import java.util.ArrayList;

/**
 * Entity representing a Response to a Written Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
public class WrittenResponse extends AbstractResponse{

    private String responseBody;

    public WrittenResponse() {
        this("Written Response.");
    }

    public WrittenResponse(String response) {
        super(QuestionType.WRITTEN);
        this.responseBody = response;
    }

    @Override
    public Object getResponseBody() {
        return responseBody;
    }

    @Override
    public void setResponseBody(Object responseBody) {
        if (responseBody instanceof String) {
            this.responseBody = (String) responseBody;
        } else {
            // TODO: Switch to using a logger
            System.out.println("Error setting response: " + responseBody);
        }
    }
}
