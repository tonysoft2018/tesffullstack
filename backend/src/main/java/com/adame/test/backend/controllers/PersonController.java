package com.adame.test.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adame.test.backend.dto.ApiResponse;
import com.adame.test.backend.entities.Person;
import com.adame.test.backend.services.PersonService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<ApiResponse<Person>> list() {
        try {
            List<Person> persons = (List<Person>) personService.findAll();
            return ResponseEntity.ok().body(new ApiResponse<>(true, "Consulta exitosa", persons));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ApiResponse<>(false, "Error en la consulta: " + e.getMessage(), null));
        }
    }

    @GetMapping("persons/{id}")
    public ResponseEntity<ApiResponse<Person>> getById(@PathVariable Long id) {
        return personService.findById(id)
                .map(person -> ResponseEntity.ok(new ApiResponse<>(true, "Persona encontrada", person)))
                .orElse(ResponseEntity.status(404).body(new ApiResponse<>(false, "Persona no encontrada", null)));
    }

    @PostMapping("/persons")
    public ResponseEntity<ApiResponse<Person>> store(@RequestBody Person person) {
        try {
            personService.create(person);
            return ResponseEntity.ok().body(new ApiResponse<>(true, "Persona creada exitosamente", List.of(person)));
        } catch (ConstraintViolationException e) {
            String errorMsg = e.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .reduce((msg1, msg2) -> msg1 + " | " + msg2)
                    .orElse("Error de validación desconocido");

            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Error al crear persona: " + errorMsg, null));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ApiResponse<>(false, "Error al crear persona: " + e.getMessage(), null));
        }
    }

    @PutMapping("persons/{id}")
    public ResponseEntity<ApiResponse<Person>> update(@PathVariable Long id, @RequestBody @Valid Person personDetails) {
        Optional<Person> updatedPerson = personService.update(id, personDetails);
        if (updatedPerson.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Persona actualizada", updatedPerson.get()));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>(false, "Persona no encontrada", null));
        }
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        boolean deleted = personService.delete(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Persona eliminada", null));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>(false, "Persona no encontrada", null));
        }
    }
}
