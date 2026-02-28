package com.meneses_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meneses_backend.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
