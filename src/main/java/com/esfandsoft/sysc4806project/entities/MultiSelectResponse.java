package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.controllers.SignupController;
import com.esfandsoft.sysc4806project.enums.QuestionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entity representing a Response to a Multi-Select Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
public class MultiSelectResponse extends AbstractResponse {

    private int optionIndex;
    private static Logger logger = LogManager.getLogger(MultiSelectResponse.class);


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
            logger.info("Error setting response: " + responseBody);
        }
    }
}
