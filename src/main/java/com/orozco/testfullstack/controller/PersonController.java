package com.orozco.testfullstack.controller;

import com.orozco.testfullstack.model.ApiResponse;
import com.orozco.testfullstack.model.Person;
import com.orozco.testfullstack.service.PersonService;
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

@RestController
@RequestMapping("/api/persons")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/findAll")
    public ApiResponse<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/findById/{id}")
    public ApiResponse<Person> findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Person> create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<Person> update(@PathVariable Long id, @RequestBody Person person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Person> delete(@PathVariable Long id) {
        return personService.delete(id);
    }
}
