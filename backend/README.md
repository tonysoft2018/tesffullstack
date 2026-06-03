# CRUD PERSON - SPRING BOOT

## Requisitos

* Java 17
* Maven 3.9+
* MySQL 8

## Base de datos

Ejecutar el script:

bd_malpica.sql

## Configuración

Editar application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/bd_malpica
spring.datasource.username=conexionTest
spring.datasource.password=ConexionTest123456*

## Ejecución

mvn clean install

mvn spring-boot:run

## Endpoints

GET /api/persons

GET /api/persons/{id}

POST /api/persons

PUT /api/persons/{id}

DELETE /api/persons/{id}

## Puerto

8080

## Evidencias

Se adjuntan capturas de Postman para:

* Crear persona
* Consultar personas
* Consultar persona por id
* Actualizar persona
* Eliminar persona
