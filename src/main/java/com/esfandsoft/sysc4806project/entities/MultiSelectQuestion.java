package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An entity representing a Multi-Select Question.
 *
 * @author Nicholas Sendyk, 101143602
 */
public class MultiSelectQuestion extends AbstractQuestion {

    private ArrayList<String> potentialAnswers;

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
            // TODO: Switch to using a logger
            System.out.println("Error setting answers: " + answers);
        }
    }
}
