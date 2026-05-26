package com.orozco.testfullstack.service.impl;

import com.orozco.testfullstack.model.ApiResponse;
import com.orozco.testfullstack.model.Person;
import com.orozco.testfullstack.repository.PersonRepository;
import com.orozco.testfullstack.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public ApiResponse<Person> findAll() {
        List<Person> persons = personRepository.findAll();
        return ApiResponse.ok("Personas obtenidas correctamente", persons);
    }

    @Override
    public ApiResponse<Person> findById(Long id) {
        return personRepository.findById(id)
                .map(person -> ApiResponse.ok("Persona encontrada", List.of(person)))
                .orElse(ApiResponse.error("Persona no encontrada con id: " + id));
    }

    @Override
    public ApiResponse<Person> create(Person person) {
        Person saved = personRepository.save(person);
        return ApiResponse.ok("Persona creada correctamente", List.of(saved));
    }

    @Override
    public ApiResponse<Person> update(Long id, Person person) {
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

    @Override
    public ApiResponse<Person> delete(Long id) {
        return personRepository.findById(id)
                .map(person -> {
                    personRepository.delete(person);
                    return ApiResponse.<Person>ok("Persona eliminada correctamente", null);
                })
                .orElseGet(() -> ApiResponse.error("Persona no encontrada con id: " + id));
    }
}
