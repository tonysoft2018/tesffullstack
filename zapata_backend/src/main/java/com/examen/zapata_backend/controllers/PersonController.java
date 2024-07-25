package com.examen.zapata_backend.controllers;

import com.examen.zapata_backend.dtos.ResponseDto;
import com.examen.zapata_backend.entities.Person;
import com.examen.zapata_backend.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllPersons() {
        try {
            List<Person> data = personService.findAll();
            if (data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto(true, "No se encontraron registros", data));
            }
            return ResponseEntity.ok(new ResponseDto(true, "Lista de personas", data));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDto(false, "Error al consultar todas las personas", new ArrayList<>()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
            List<Person> data = new ArrayList<>();
        try {
            Person person = personService.findById(id);
            if (person == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto(true, "No se encontró una persona con id " + id, data));
            }
            data.add(person);
            return ResponseEntity.ok(new ResponseDto(true, "Persona encontrada", data));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDto(false, "Error al obtener persona por id", data));
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDto> savePerson(@RequestBody Person person) {
        ArrayList<Person> data = new ArrayList<>();
        try {
            Person savedPerson = personService.save(person);
            if (savedPerson == null) {
                return ResponseEntity.badRequest()
                        .body(new ResponseDto(true, "No se creó, faltan atributos de la persona", data));
            }
            data.add(savedPerson);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(true, "Creación correcta", data));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDto(false, "Error al guardar la persona", data));
        }
    }
    
    @PutMapping("{id}")
    public ResponseEntity<ResponseDto> updatePerson(@RequestBody Person person, @PathVariable Long id) {
        ArrayList<Person> data = new ArrayList<>();
        try {
            if (personService.findById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto(true, "No se encontró una persona con id " + id, data));
            }
            Person updatedPerson = personService.update(person, id);
            if (updatedPerson == null) {
                return ResponseEntity.badRequest()
                        .body(new ResponseDto(true, "No se actualizó, faltan atributos de la persona", data));
            }
            data.add(updatedPerson);
            return ResponseEntity.ok(new ResponseDto(true, "Actualización correcta", data));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDto(false, "Error al actualizar persona con id " + id, data));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deletePerson(@PathVariable Long id) {
        ArrayList<Person> data = new ArrayList<>();
        try {
            Person personFound = personService.findById(id);
            if ( personFound == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDto(true, "No se encontró una persona con id " + id, data));
            }
            personService.deleteById(id);
            data.add(personFound);
            return ResponseEntity.ok(new ResponseDto(true, "Eliminación correcta", data));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseDto(false, "Error al eliminar persona con id " + id, data));
        }
    }
}
