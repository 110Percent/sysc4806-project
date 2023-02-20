package com.esfandsoft.sysc4806project.entities;
import com.esfandsoft.sysc4806project.enums.QuestionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity representing a Response to a Question.
 *
 * @author Ethan Houlahan, 101145675
 * @author Nicholas Sendyk, 101143602
 */
@Entity
public class Response {

    @Id
    @GeneratedValue
    long id;

    private QuestionType responseType;
    private Object responseBody;

    /**
     * Default constructor for Response
     */
    public Response() {
        responseType = QuestionType.MULTISELECT;  // Default Value
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }

    public QuestionType getResponseType() {
        return responseType;
    }

    public void setResponseType(QuestionType responseType) {
        this.responseType = responseType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
