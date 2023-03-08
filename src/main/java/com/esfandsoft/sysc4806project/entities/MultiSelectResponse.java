package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entity representing a Response to a Multi-Select Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
@Entity
public class MultiSelectResponse extends AbstractResponse {

    private static final Logger logger = LogManager.getLogger(MultiSelectResponse.class);
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
    public void setResponseBody(Object responseBody) {

        if (responseBody instanceof Integer) {
            this.optionIndex = (Integer) responseBody;
        } else if (responseBody instanceof String) {
            try {
                this.optionIndex = Integer.parseInt((String) responseBody);
            } catch (NumberFormatException e) {
                logger.info("Error parsing string to integer: " + responseBody);
            }

        } else {
            logger.info("Error setting response: " + responseBody);
        }
    }
}
