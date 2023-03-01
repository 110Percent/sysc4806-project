package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity representing a Response to a Numeric Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
public class NumericResponse extends AbstractResponse{

    public NumericResponse() {
        this(404);
    }

    public NumericResponse(Integer responseBody) {
        super(QuestionType.NUMERIC, responseBody);
    }

}

