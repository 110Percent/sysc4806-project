package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;

import java.util.stream.IntStream;

/**
 * An entity representing a Numeric Question.
 *
 * @author Nicholas Sendyk, 101143602
 */
public class NumericQuestion extends AbstractQuestion {

    private int max;
    private int min;
    private int[] potentialAnswers;

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
        setAnswers(a);
    }

    @Override
    public void setAnswers(Object answers) {
        if (answers instanceof int[]) {
            this.potentialAnswers = (int[]) answers;
        } else {
            // TODO: Switch to using a logger
            System.out.println("Error setting answers: " + answers);
        }
    }

    @Override
    public Object getAnswers() {
        return potentialAnswers;
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
