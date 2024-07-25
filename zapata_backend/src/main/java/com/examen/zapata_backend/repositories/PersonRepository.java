package com.examen.zapata_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<com.examen.zapata_backend.entities.Person, Long> {
}
