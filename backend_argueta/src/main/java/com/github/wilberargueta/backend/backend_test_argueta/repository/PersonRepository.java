package com.github.wilberargueta.backend.backend_test_argueta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.wilberargueta.backend.backend_test_argueta.repository.entity.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}
