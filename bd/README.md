# meneses_bd

Script de base de datos para el examen Fullstack Móvil.

Este módulo contiene la configuración y creación de la base de datos en MySQL, así como la tabla requerida para el manejo de personas.

---

# Descripción

Se crea una base de datos llamada: bd_meneses Y una tabla llamada: person


Con los siguientes campos:

- id (autoincremental)
- nombre
- apellido
- fechaNacimiento
- puesto
- sueldo

---

# Requisitos

- MySQL 8+
- MySQL Workbench

---

# Script de Base de Datos

```sql
-- Crear base de datos
CREATE DATABASE bd_meneses;

-- Usar base de datos
USE bd_meneses;

-- Crear tabla person
CREATE TABLE person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    puesto VARCHAR(100) NOT NULL,
    sueldo DOUBLE NOT NULL
);


