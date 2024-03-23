package com.garcia.testfullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garcia.testfullstack.model.PersonEntity;
import com.garcia.testfullstack.model.Response;
import com.garcia.testfullstack.repository.PersonRepository;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200/")
    public Response<PersonEntity> getAllPersons() {
        List<PersonEntity> persons = personRepository.findAll();
        return new Response<PersonEntity>(true, "Personas encontradas", persons);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200/")
    public Response<PersonEntity> getPersonById(@PathVariable Long id) {
        PersonEntity person = personRepository.findById(id).orElse(null);
        if (person != null) {
            return new Response<>(true, "Persona encontrada", List.of(person));
        } else {
            return new Response<>(false, "Persona no encontrada", null);
        }
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200/")
    public Response<PersonEntity> createPerson(@RequestBody PersonEntity person) {
        PersonEntity savedPerson = personRepository.save(person);
        return new Response<>(true, "Persona creada", List.of(savedPerson));
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200/")
    public Response<PersonEntity> updatePerson(@PathVariable Long id, @RequestBody PersonEntity personDetails) {
        PersonEntity person = personRepository.findById(id).orElse(null);
        if (person != null) {
            person.nombre = personDetails.nombre;
            person.apellido = personDetails.apellido;
            person.fechaNacimiento = personDetails.fechaNacimiento;
            person.puesto = personDetails.puesto;
            person.sueldo = personDetails.sueldo;
            PersonEntity updatedPerson = personRepository.save(person);
            return new Response<>(true, "Persona actualizada", List.of(updatedPerson));
        } else {
            return new Response<>(false, "Persona no encontrada", null);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200/")
    public Response<PersonEntity> deletePerson(@PathVariable Long id) {
        PersonEntity person = personRepository.findById(id).orElse(null);
        if (person != null) {
            personRepository.delete(person);
            return new Response<>(true, "Persona eliminada", null);
        } else {
            return new Response<>(false, "Persona no encontrada", null);
        }
    }

}
