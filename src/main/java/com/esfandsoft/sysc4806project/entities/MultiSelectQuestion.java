package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An entity representing a Multi-Select Question.
 *
 * @author Nicholas Sendyk, 101143602
 */
@Entity
public class MultiSelectQuestion extends AbstractQuestion {

    private static final Logger logger = LogManager.getLogger(MultiSelectQuestion.class);
    private String[] potentialAnswers;
    private int[] results;

    /**
     * Default constructor for Multi-Select Question
     */
    public MultiSelectQuestion() {
        this("Default Multi-Select Query?", new String[]{"A", "B", "C"});
    }

    public MultiSelectQuestion(String query, String[] options) {
        super(QuestionType.MULTISELECT, query);
        this.potentialAnswers = options;
        this.results = new int[]{};
    }


    public String[] getPotentialAnswers() {
        return potentialAnswers;
    }


    public void setPotentialAnswers(String[] answers) {
        this.potentialAnswers = answers;
    }

    @Override
    public void initResultsGeneration() {
        this.results = generateResults();
    }

    public int[] getResults() {
        return results;
    }

    public void setResults(int[] results) {
        this.results = results;
    }

    /**
     * Generate the results for a multi-select question
     *
     * @return String[] - Each index represents an answer, containing the percentage of respondents which selected it
     */
    private int[] generateResults() {
        int sizeOfAnswerBank = this.potentialAnswers.length;

        int[] rs = new int[sizeOfAnswerBank];

        for (int th = 0; th < sizeOfAnswerBank; th++) {
            rs[th] = 0;
        }

        for (AbstractResponse ar : this.responses) {
            rs[(int) ar.getResponseBody()] = rs[(int) ar.getResponseBody()] + 1;
        }

        return rs;
    }

    @Override
    public void printResponses() {
        for (AbstractResponse r : this.responses) {
            logger.info("Response #" + r.getId() + ": " + this.potentialAnswers[(int) r.getResponseBody()]);
        }
    }
}
