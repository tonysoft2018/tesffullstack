package com.joya.testfullstack.controller;

import com.joya.testfullstack.model.ApiResponse;
import com.joya.testfullstack.model.Person;
import com.joya.testfullstack.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ApiResponse<Person> findAll() {
        List<Person> persons = personRepository.findAll();
        return ApiResponse.ok("Personas obtenidas correctamente", persons);
    }

    @GetMapping("/{id}")
    public ApiResponse<Person> findById(@PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> ApiResponse.ok("Persona encontrada", List.of(person)))
                .orElse(ApiResponse.error("Persona no encontrada con id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Person> create(@RequestBody Person person) {
        Person saved = personRepository.save(person);
        return ApiResponse.ok("Persona creada correctamente", List.of(saved));
    }

    @PutMapping("/{id}")
    public ApiResponse<Person> update(@PathVariable Long id, @RequestBody Person person) {
        return personRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(person.getNombre());
                    existing.setApellido(person.getApellido());
                    existing.setFechaNacimiento(person.getFechaNacimiento());
                    existing.setPuesto(person.getPuesto());
                    existing.setSueldo(person.getSueldo());
                    Person updated = personRepository.save(existing);
                    return ApiResponse.ok("Persona actualizada correctamente", List.of(updated));
                })
                .orElse(ApiResponse.error("Persona no encontrada con id: " + id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Person> delete(@PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    personRepository.delete(person);
                    return ApiResponse.<Person>ok("Persona eliminada correctamente", null);
                })
                .orElseGet(() -> ApiResponse.error("Persona no encontrada con id: " + id));
    }
}
