package com.adame.test.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adame.test.backend.entities.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
