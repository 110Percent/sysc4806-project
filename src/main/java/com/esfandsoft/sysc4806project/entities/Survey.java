package com.esfandsoft.sysc4806project.entities;
import com.esfandsoft.sysc4806project.entities.Question;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Entity representing a Survey.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
@Entity
public class Survey {

    @Id
    @GeneratedValue
    long id;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    Collection<Question> surveyQuestions;

    private String surveyTitle;

    /**
     * Default constructor for Survey
     */
    public Survey() {
        this("Default Survey");
    }

    public Survey(String surveyTitle) {
        this.surveyTitle = surveyTitle;
        surveyQuestions = new ArrayList<Question>();
    }

    public Collection<Question> getSurveyQuestions() {
        return surveyQuestions;
    }

    public void setSurveyQuestions(Collection<Question> surveyQuestions) {
        this.surveyQuestions = surveyQuestions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Adds a singular question to a survey
     *
     * @author Nicholas Sendyk, 101143602
     * @param question the Question to add to the Survey
     */
    public void addSurveyQuestion(Question question){
        surveyQuestions.add(question);
    }

    /**
     * Adds an ArrayList of Questions to the surveyQuestions
     *
     * @author Nicholas Sendyk, 101143602
     * @param questions list of questions to add
     */
    public void addListSurveyQuestions(ArrayList<Question> questions) {
        for (Question q: questions) {
            addSurveyQuestion(q);
        }
    }
}
