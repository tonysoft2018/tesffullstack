package com.github.wilberargueta.backend.backend_test_argueta.dto.request.person;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PersonCreateDto {

    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    @NotBlank(message = "Apellido es requerido")
    private String apellido;
    @NotNull(message = "Fecha de nacimiento es requerido")
    private LocalDate fechaNacimiento;
    @NotBlank(message = "Puesto es requerido")
    private String puesto;
    @Min(value = 1, message = "Sueldo es requerido")
    private BigDecimal sueldo;
}
