package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class NumericResponse extends AbstractResponse{
    private Object responseBody;

    private int number;

    public NumericResponse() {
        this(QuestionType.NUMERIC, null);
    }

    public NumericResponse(QuestionType responseType, Object responseBody) {
        super(responseType, responseBody);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    public QuestionType getResponseType() {
        return super.getResponseType();
    }

    public void setResponseType(QuestionType responseType) {
        super.setResponseType(responseType);
    }
}

