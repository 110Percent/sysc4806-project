package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;

/**
 * Entity representing a Response to a Written Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
public class WrittenResponse extends AbstractResponse{

    public WrittenResponse() {
        this("Written Response.");
    }

    public WrittenResponse(String response) {
        super(QuestionType.WRITTEN, response);
    }
}
