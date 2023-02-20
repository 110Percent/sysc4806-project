package com.esfandsoft.sysc4806project.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class User {

    @Id
    @GeneratedValue
    long id;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    Collection<Survey> surveys;
}
