package com.adame.test.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adame.test.backend.entities.Person;
import com.adame.test.backend.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Iterable<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return this.personRepository.findById(id);
    }

    public Person create(Person person) {
        this.personRepository.save(person);

        return person;
    }

    public Optional<Person> update(Long id, Person personDetails) {
        return personRepository.findById(id).map(person -> {
            person.setName(personDetails.getName());
            person.setLastname(personDetails.getLastname());
            person.setBirdDate(personDetails.getBirdDate());
            person.setPosition(personDetails.getPosition());
            person.setSalary(personDetails.getSalary());
            return personRepository.save(person);
        });
    }

    public boolean delete(Long id) {
        return personRepository.findById(id).map(person -> {
            personRepository.delete(person);
            return true;
        }).orElse(false);
    }
}
