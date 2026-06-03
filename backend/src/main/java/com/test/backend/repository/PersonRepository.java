package com.test.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.backend.entity.Person;

public interface PersonRepository
        extends JpaRepository<Person, Integer> {
}
