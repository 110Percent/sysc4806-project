package com.esfandsoft.sysc4806project.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue
    long id;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    Collection<Survey> surveys;
}
