package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entity representing a Response to a Numeric Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
public class NumericResponse extends AbstractResponse {

    private Integer response;
    private Logger logger = LogManager.getLogger(NumericResponse.class);

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
            logger.info("Error setting response: " + responseBody);
        }
    }

}

