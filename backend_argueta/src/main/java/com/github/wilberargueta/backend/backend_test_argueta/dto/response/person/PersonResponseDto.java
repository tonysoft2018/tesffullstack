package com.github.wilberargueta.backend.backend_test_argueta.dto.response.person;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class PersonResponseDto {

    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String puesto;
    private BigDecimal sueldo;

}
