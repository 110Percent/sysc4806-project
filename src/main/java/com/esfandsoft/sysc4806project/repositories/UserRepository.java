package com.esfandsoft.sysc4806project.repositories;

import com.esfandsoft.sysc4806project.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameIgnoreCase(String username);
}
