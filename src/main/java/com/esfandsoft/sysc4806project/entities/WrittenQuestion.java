package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity representing a Written Question.
 *
 * @author Nicholas Sendyk, 101143602
 */

@Entity
public class WrittenQuestion extends AbstractQuestion {

    private static final Logger logger = LogManager.getLogger(WrittenQuestion.class);

    /**
     * Default constructor for Written Questions
     */
    public WrittenQuestion() {
        this("Default Written Query?");
    }

    public WrittenQuestion(String query) {
        super(QuestionType.WRITTEN, query);
    }

    @Override
    public Object getAnswers() {
        return null;
    }

    @Override
    public void setAnswers(Object answers) {
        logger.info("Not possible to set answers for Written Questions: " + answers);
    }

    /**
     * Generate the results for a written question
     *
     * @return List<String> - A list of every response
     */
    @Override
    String[] generateResults() {
        int sizeOfAnswerBank = this.responses.size();
        String[] rs = new String[sizeOfAnswerBank];
        int i = 0;
        for (AbstractResponse ar : this.responses) {
            rs[i] = ((String) ar.getResponseBody());
            i++;
        }
        return rs;
    }
}
