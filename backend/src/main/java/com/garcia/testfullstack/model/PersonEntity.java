package com.garcia.testfullstack.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nombre;
    public String apellido;
    public Date fechaNacimiento;
    public String puesto;
    public BigDecimal sueldo;

    public void Person() {
    }

    public void Person(String nombre, String apellido, Date fechaNacimiento, String puesto, BigDecimal sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.puesto = puesto;
        this.sueldo = sueldo;
    }
}
