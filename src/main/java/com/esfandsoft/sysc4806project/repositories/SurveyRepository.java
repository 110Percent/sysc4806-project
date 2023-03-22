package com.esfandsoft.sysc4806project.repositories;

import com.esfandsoft.sysc4806project.entities.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Survey findById(long id);
}
