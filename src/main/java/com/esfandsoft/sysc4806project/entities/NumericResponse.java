package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class NumericResponse extends AbstractResponse{
    public NumericResponse() {
        this(404);
    }

    public NumericResponse(Integer responseBody) {
        super(QuestionType.NUMERIC, responseBody);
    }

}

