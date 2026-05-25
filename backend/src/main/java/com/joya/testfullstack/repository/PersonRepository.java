package com.joya.testfullstack.repository;

import com.joya.testfullstack.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
