package com.orozco.testfullstack.repository;

import com.orozco.testfullstack.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
