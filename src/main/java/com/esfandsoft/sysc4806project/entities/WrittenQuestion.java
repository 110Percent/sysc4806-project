package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An entity representing a Written Question.
 *
 * @author Nicholas Sendyk, 101143602
 */

@Entity
public class WrittenQuestion extends AbstractQuestion {

    private static final Logger logger = LogManager.getLogger(WrittenQuestion.class);
    private String[] results;

    /**
     * Default constructor for Written Questions
     */
    public WrittenQuestion() {
        this("Default Written Query?");
    }

    public WrittenQuestion(String query) {
        super(QuestionType.WRITTEN, query);
        this.results = new String[]{};
    }


    public String[] getAnswers() {
        return new String[0];
    }


    public void setAnswers(String[] answers) {
        logger.info("Not possible to set answers for Written Questions: " + answers);
    }

    @Override
    public void initResultsGeneration() {
        this.results = generateResults();
    }

    public String[] getResults() {
        return results;
    }

    public void setResults(String[] results) {
        this.results = results;
    }

    /**
     * Generate the results for a written question
     *
     * @return List<String> - A list of every response
     */
    private String[] generateResults() {
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
