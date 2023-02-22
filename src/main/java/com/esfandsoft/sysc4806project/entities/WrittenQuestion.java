package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;

/**
 * An entity representing a Written Question.
 *
 * @author Nicholas Sendyk, 101143602
 */
public class WrittenQuestion extends AbstractQuestion {

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
        // TODO: Switch to using a logger
        System.out.println("Not possible to set answers for Written Questions: " + answers);
    }
}
