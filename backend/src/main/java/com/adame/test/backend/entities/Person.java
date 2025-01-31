package com.adame.test.backend.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    private String lastname;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate birdDate;

    @NotBlank(message = "El puesto no puede estar vacío")
    @Size(min = 2, max = 100, message = "El puesto debe tener entre 2 y 100 caracteres")
    private String position;

    @NotNull(message = "El salario no puede ser nulo")
    @Positive(message = "El salario debe ser positivo")
    private BigDecimal salary;

    public Person() {
    }

    public Person(Long id, String name, String lastname, LocalDate birdDate, String position, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birdDate = birdDate;
        this.position = position;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirdDate() {
        return birdDate;
    }

    public void setBirdDate(LocalDate birdDate) {
        this.birdDate = birdDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
