package com.esfandsoft.sysc4806project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


/**
 *
 *
 * @Author Ethan Houlahan 101145675
 */
@Entity
public class MultiSelectOption {

    @Id
    @GeneratedValue
    long Id;

    private String answer;

    private boolean isCorrect;

    public MultiSelectOption(String answer, boolean isCorrect){
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public MultiSelectOption() {
        this.answer = "Default Answer";
        this.isCorrect = false;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public void setCorrect(boolean isCorrect){
        this.isCorrect = isCorrect;
    }

    public String getAnswer(){
        return this.answer;
    }
    public boolean getCorrect(){
        return isCorrect;
    }
}
