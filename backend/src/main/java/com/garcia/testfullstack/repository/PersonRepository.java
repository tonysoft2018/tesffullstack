package com.garcia.testfullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.garcia.testfullstack.model.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
