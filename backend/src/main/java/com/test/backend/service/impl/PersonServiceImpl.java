package com.test.backend.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.test.backend.entity.Person;
import com.test.backend.repository.PersonRepository;
import com.test.backend.service.interfaces.PersonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Person findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Persona no encontrada"));
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public Person update(Integer id, Person person) {

        Person current = findById(id);

        current.setNombre(person.getNombre());
        current.setApellido(person.getApellido());
        current.setFechaNacimiento(person.getFechaNacimiento());
        current.setPuesto(person.getPuesto());
        current.setSueldo(person.getSueldo());

        return repository.save(current);
    }

    @Override
    public void delete(Integer id) {

        Person person = findById(id);

        repository.delete(person);
    }
}
