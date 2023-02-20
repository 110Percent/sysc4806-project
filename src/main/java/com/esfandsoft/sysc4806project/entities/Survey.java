package com.esfandsoft.sysc4806project.entities;
import com.esfandsoft.sysc4806project.entities.Question;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Survey {

    @Id
    @GeneratedValue
    long id;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    Collection<Question> surveyQuestions;
}
