package com.test.backend.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.test.backend.entity.Person;
import com.test.backend.response.ApiResponse;
import com.test.backend.service.interfaces.PersonService;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping
    public ApiResponse findAll() {

        return ApiResponse.builder()
                .status(true)
                .msg("Consulta exitosa")
                .data(service.findAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse findById(
            @PathVariable Integer id) {

        return ApiResponse.builder()
                .status(true)
                .msg("Consulta exitosa")
                .data(service.findById(id))
                .build();
    }

    @PostMapping
    public ApiResponse save(
            @RequestBody Person person) {

        return ApiResponse.builder()
                .status(true)
                .msg("Persona creada correctamente")
                .data(service.save(person))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse update(
            @PathVariable Integer id,
            @RequestBody Person person) {

        return ApiResponse.builder()
                .status(true)
                .msg("Persona actualizada correctamente")
                .data(service.update(id, person))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(
            @PathVariable Integer id) {

        service.delete(id);

        return ApiResponse.builder()
                .status(true)
                .msg("Persona eliminada correctamente")
                .data(null)
                .build();
    }
}
