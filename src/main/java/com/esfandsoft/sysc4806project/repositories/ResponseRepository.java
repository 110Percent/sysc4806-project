package com.esfandsoft.sysc4806project.repositories;

import com.esfandsoft.sysc4806project.entities.AbstractResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends CrudRepository<AbstractResponse, Long> {
    AbstractResponse findById(long id);
}
