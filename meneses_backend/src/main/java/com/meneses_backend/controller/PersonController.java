package com.meneses_backend.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.meneses_backend.model.Person;
import com.meneses_backend.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    // GET all
    @GetMapping
    public List<Person> getAll() {
        return repository.findAll();
    }

    // GET by id
    @GetMapping("/{id}")
    public Optional<Person> getById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    // POST create
    @PostMapping
    public Person create(@RequestBody Person person) {
        return repository.save(person);
    }

    // PUT update
    @PutMapping("/{id}")
    public Person update(@PathVariable Integer id, @RequestBody Person person) {
        person.setId(id);
        return repository.save(person);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}