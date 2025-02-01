package com.stefanini.personal.domain.repository;

import com.stefanini.personal.persistance.entitys.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}