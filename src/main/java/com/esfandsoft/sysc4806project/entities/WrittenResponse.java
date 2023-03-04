package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entity representing a Response to a Written Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */

@Entity
public class WrittenResponse extends AbstractResponse {

    private String responseBody;
    private static Logger logger = LogManager.getLogger(WrittenResponse.class);

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
            logger.info("Error setting response: " + responseBody);
        }
    }
}
