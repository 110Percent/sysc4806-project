package com.esfandsoft.sysc4806project.entities;

import com.esfandsoft.sysc4806project.enums.QuestionType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

/**
 * Abstract Entity Representing a question response
 *
 * @author Ethan Houlahan 101145675
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "responseType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultiSelectResponse.class, name = "MULTISELECT"),
        @JsonSubTypes.Type(value = NumericResponse.class, name = "NUMERIC"),
        @JsonSubTypes.Type(value = WrittenResponse.class, name = "WRITTEN")
})
public abstract class AbstractResponse {

    @Id
    @GeneratedValue
    long id;
    private QuestionType responseType;

    protected AbstractResponse() {
        this(QuestionType.MULTISELECT);
    }

    protected AbstractResponse(QuestionType responseType) {
        this.responseType = responseType;
    }

    //public abstract QuestionType getType();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public abstract Object getResponseBody();

    public abstract void setResponseBody(Object responseBody);

    public QuestionType getResponseType() {
        return responseType;
    }

    public void setResponseType(QuestionType responseType) {
        this.responseType = responseType;
    }
}
