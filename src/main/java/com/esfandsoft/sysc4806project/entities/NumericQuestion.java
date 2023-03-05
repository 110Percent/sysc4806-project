package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * An entity representing a Numeric Question.
 *
 * @author Nicholas Sendyk, 101143602
 */

@Entity
public class NumericQuestion extends AbstractQuestion {

    private static final Logger logger = LogManager.getLogger(NumericResponse.class);
    private int max;
    private int min;
    private String[] potentialAnswers;

    /**
     * Default constructor for Numeric Question
     */
    public NumericQuestion() {
        this("Default Numeric Query?", 0, 10);
    }

    public NumericQuestion(String query, int min, int max) {
        super(QuestionType.NUMERIC, query);
        this.min = min;
        this.max = max;
        generateAnswers(min, max);
    }

    /**
     * Generate the list of possible answers
     *
     * @param min the low boundary of possible answers
     * @param max the high boundary for possible answers
     */
    private void generateAnswers(int min, int max) {
        int[] a = IntStream.range(min, max).toArray();
        setAnswers(Arrays.stream(a).mapToObj(String::valueOf).toArray(String[]::new));
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
     * Generate the results for a numeric question
     *
     * @return String[] - Each index represents an answer, containing the number of respondents which selected it
     */
    @Override
    String[] generateResults() {
        int sizeOfAnswerBank = this.max - this.min + 1;
        int[] rs = new int[sizeOfAnswerBank];
        String[] srs = new String[sizeOfAnswerBank];

        for (int th = 0; th < sizeOfAnswerBank; th++) {
            rs[th] = 0;
        }

        for (AbstractResponse ar : this.responses) {
            int idx = (int) ar.getResponseBody() - this.min;
            rs[idx] = rs[idx] + 1;
        }

        for (int i = 0; i < rs.length; i++) {
            srs[i] = Integer.toString(rs[i]);
        }

        return srs;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        generateAnswers(this.min, this.max);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
        generateAnswers(this.min, this.max);
    }
}
