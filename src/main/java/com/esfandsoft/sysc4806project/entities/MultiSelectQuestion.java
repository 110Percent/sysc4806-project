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

    private List<String> potentialAnswers;
    private static Logger logger = LogManager.getLogger(MultiSelectQuestion.class);

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

    @Override
    public void printResponses() {
        for (AbstractResponse r: this.responses) {
            logger.info("Response #" + r.getId() +": " + this.potentialAnswers.get((int) r.getResponseBody()));
        }
    }
}
