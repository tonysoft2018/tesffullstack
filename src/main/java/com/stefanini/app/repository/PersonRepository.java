package com.stefanini.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stefanini.app.dto.PersonListDto;
import com.stefanini.app.dto.PersonRequestDto;
import com.stefanini.app.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

    @Query("""
            SELECT new com.stefanini.app.dto.PersonRequestDto(
            p.id AS id, p.nombre AS nombre, p.apellido AS apellido,
            p.fechaNacimiento AS fechaNacimiento, p.puesto AS puesto, 
            p.sueldo AS sueldo
            ) 
            FROM Person p 
            WHERE p.id = :id
            """)
    PersonRequestDto getByIdPerson(@Param("id") Integer id);
    
    @Query("""
            SELECT new com.stefanini.app.dto.PersonListDto(
            p.id AS id, p.nombre AS nombre, p.apellido AS apellido,
            p.fechaNacimiento AS fechaNacimiento, p.puesto AS puesto, 
            p.sueldo AS sueldo
            ) 
            FROM Person p  
            """)
    List<PersonListDto> getAll();
}
