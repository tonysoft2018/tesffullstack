package com.stefanini.personal.web.controller;

import com.stefanini.personal.domain.dto.ApiResponse;
import com.stefanini.personal.domain.service.PersonService;
import com.stefanini.personal.domain.dto.PersonDTO;
import com.stefanini.personal.exception.CreatePersonException;
import com.stefanini.personal.exception.NoDataFoundException;
import com.stefanini.personal.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<ApiResponse<PersonDTO>> createPerson(@RequestBody PersonDTO personDTO) {
        try {
            System.out.println(personDTO);
            System.out.println("personDTO2");
            PersonDTO createdPerson = personService.createPerson(personDTO);
            ApiResponse<PersonDTO> response = new ApiResponse<>(true, "Persona creada exitosamente", createdPerson);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (CreatePersonException ex) {
            System.out.println(ex.getMessage());
            ApiResponse<PersonDTO> response = new ApiResponse<>(false, ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonDTO>> getPersonById(@PathVariable Integer id) {
        try {
            PersonDTO personDTO = personService.getPersonById(id);
            ApiResponse<PersonDTO> response = new ApiResponse<>(true, "Persona encontrada", personDTO);
            return ResponseEntity.ok(response);
        } catch (PersonNotFoundException ex) {
            ApiResponse<PersonDTO> response = new ApiResponse<>(false,"Persona no encontrada", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PersonDTO>>> getAllPersons() {
        try {
            List<PersonDTO> persons = personService.getAllPersons();
            ApiResponse<List<PersonDTO>> response = new ApiResponse<>(true, "Datos encontrados", persons);
            return ResponseEntity.ok(response);
        } catch (NoDataFoundException ex) {
            ApiResponse<List<PersonDTO>> response = new ApiResponse<>(false, ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Integer id, @RequestBody PersonDTO personDTO) {
        PersonDTO updatedPerson = personService.updatePerson(id, personDTO);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deletePerson(@PathVariable Integer id) {
        try {
            personService.deletePerson(id);
            ApiResponse<?> response = new ApiResponse<>(true, "Persona eliminada exitosamente", null);
            return ResponseEntity.ok(response);
        } catch (PersonNotFoundException ex) {
            ApiResponse<?> response = new ApiResponse<>(false, ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}