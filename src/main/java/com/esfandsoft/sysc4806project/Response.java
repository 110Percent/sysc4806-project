package com.esfandsoft.sysc4806project;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Response {

    @Id
    @GeneratedValue
    long id;

    @ManyToOne
    Question question;
}
