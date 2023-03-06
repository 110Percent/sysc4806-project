package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entity representing a Response to a Numeric Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */

@Entity
public class NumericResponse extends AbstractResponse {

    private static final Logger logger = LogManager.getLogger(NumericResponse.class);
    private Integer responseBody;

    public NumericResponse() {
        this(404);
    }

    public NumericResponse(Integer responseBody) {
        super(QuestionType.NUMERIC);
        this.responseBody = responseBody;
    }

    @Override
    public Object getResponseBody() {
        return responseBody;
    }

    @Override
    public void setResponseBody(Object responseBody) {
        if (responseBody instanceof Integer) {
            this.responseBody = (Integer) responseBody;
        } else {
            logger.info("Error setting response: " + responseBody);
        }
    }

}

