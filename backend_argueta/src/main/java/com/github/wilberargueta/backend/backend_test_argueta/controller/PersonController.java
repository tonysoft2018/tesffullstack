package com.github.wilberargueta.backend.backend_test_argueta.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.wilberargueta.backend.backend_test_argueta.dto.request.person.PersonCreateDto;
import com.github.wilberargueta.backend.backend_test_argueta.dto.request.person.PersonUpdateDto;
import com.github.wilberargueta.backend.backend_test_argueta.dto.response.GenericResponse;
import com.github.wilberargueta.backend.backend_test_argueta.dto.response.PaginatedResponse;
import com.github.wilberargueta.backend.backend_test_argueta.dto.response.person.PersonResponseDto;
import com.github.wilberargueta.backend.backend_test_argueta.service.PersonService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<GenericResponse<PersonResponseDto>> createPerson(
            @Valid @RequestBody PersonCreateDto createDto) {
        return ResponseEntity.ok(GenericResponse.ok(this.personService.createPerson(createDto)));
    }

    @PutMapping
    public ResponseEntity<GenericResponse<PersonResponseDto>> updatePerson(
            @Valid @RequestBody PersonUpdateDto createDto) {
        return ResponseEntity.ok(GenericResponse.ok(this.personService.updatePerson(createDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Boolean>> deletePerson(@PathVariable Long id) {
        Boolean deletePerson = this.personService.deletePerson(id);
        return ResponseEntity.ok(GenericResponse.ok(deletePerson));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<PersonResponseDto>> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok(GenericResponse.ok(this.personService.getPerson(id)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<PaginatedResponse<PersonResponseDto>>> getPerson(Pageable pageable) {
        return ResponseEntity.ok(GenericResponse.ok(this.personService.getPerson(pageable)));
    }

}
