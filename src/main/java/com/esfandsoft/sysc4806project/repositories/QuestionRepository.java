package com.esfandsoft.sysc4806project.repositories;

import com.esfandsoft.sysc4806project.entities.AbstractQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<AbstractQuestion, Long> {
    AbstractQuestion findById(long id);
}
