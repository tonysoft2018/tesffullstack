package com.github.wilberargueta.backend.backend_test_argueta.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "person")
@Entity
@Getter
@Setter
@ToString
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String puesto;
    private BigDecimal sueldo;

}
