package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An entity representing a Multi-Select Question.
 *
 * @author Nicholas Sendyk, 101143602
 */
@Entity
public class MultiSelectQuestion extends AbstractQuestion {

    private static final Logger logger = LogManager.getLogger(MultiSelectQuestion.class);
    private List<String> potentialAnswers;

    /**
     * Default constructor for Multi-Select Question
     */
    public MultiSelectQuestion() {
        this("Default Multi-Select Query?", new ArrayList<String>(Arrays.asList("A", "B", "C")));
    }

    public MultiSelectQuestion(String query, ArrayList<String> options) {
        super(QuestionType.MULTISELECT, query);
        this.potentialAnswers = options;
    }

    @Override
    public Object getAnswers() {
        return potentialAnswers;
    }

    @Override
    public void setAnswers(Object answers) {
        if (answers instanceof ArrayList) {
            this.potentialAnswers = (ArrayList<String>) answers;
        } else {
            logger.info("Error setting answers: " + answers);
        }
    }

    /**
     * Generate the results for a multi-select question
     *
     * @return String[] - Each index represents an answer, containing the percentage of respondents which selected it
     */
    @Override
    String[] generateResults() {
        int sizeOfAnswerBank = this.potentialAnswers.size();

        int[] rs = new int[sizeOfAnswerBank];
        int[] frs = new int[sizeOfAnswerBank];
        String[] sfrs = new String[sizeOfAnswerBank];

        for (int th = 0; th < sizeOfAnswerBank; th++) {
            rs[th] = 0;
        }

        for (AbstractResponse ar : this.responses) {
            int idx = this.potentialAnswers.indexOf(ar.getResponseBody());
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
            logger.info("Response #" + r.getId() + ": " + this.potentialAnswers.get((int) r.getResponseBody()));
        }
    }
}
