package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * An entity representing a Multi-Select Question.
 *
 * @author Nicholas Sendyk, 101143602
 */
@Entity
public class MultiSelectQuestion extends AbstractQuestion {

    private static final Logger logger = LogManager.getLogger(MultiSelectQuestion.class);
    private String[] potentialAnswers;

    /**
     * Default constructor for Multi-Select Question
     */
    public MultiSelectQuestion() {
        this("Default Multi-Select Query?", new String[]{"A", "B", "C"});
    }

    public MultiSelectQuestion(String query, String[] options) {
        super(QuestionType.MULTISELECT, query);
        this.potentialAnswers = options;
    }

    @Override
    public String[] getAnswers() {
        return potentialAnswers;
    }

    @Override
    public void setAnswers(String[] answers) {
        this.potentialAnswers = answers;
    }

    /**
     * Generate the results for a multi-select question
     *
     * @return String[] - Each index represents an answer, containing the percentage of respondents which selected it
     */
    @Override
    String[] generateResults() {
        int sizeOfAnswerBank = this.potentialAnswers.length;

        int[] rs = new int[sizeOfAnswerBank];
        int[] frs = new int[sizeOfAnswerBank];
        String[] sfrs = new String[sizeOfAnswerBank];

        for (int th = 0; th < sizeOfAnswerBank; th++) {
            rs[th] = 0;
        }

        for (AbstractResponse ar : this.responses) {
            int idx = Arrays.stream(this.potentialAnswers).toList()
                    .indexOf(ar.getResponseBody());
            rs[idx] = rs[idx] + 1;
        }

        for (int i = 0; i < sizeOfAnswerBank; i++) {
            if (sizeOfAnswerBank != 0) {
                frs[i] = rs[i] / sizeOfAnswerBank;
            }
            sfrs[i] = Integer.toString(frs[i]);
        }

        return sfrs;
    }

    @Override
    public void printResponses() {
        for (AbstractResponse r : this.responses) {
            logger.info("Response #" + r.getId() + ": " + this.potentialAnswers[(int) r.getResponseBody()]);
        }
    }
}
