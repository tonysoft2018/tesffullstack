CREATE DATABASE `bd_castellanos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE bd_castellanos;

CREATE USER 'conexion'@'localhost' IDENTIFIED BY 'conexion123';
GRANT ALL PRIVILEGES ON bd_castellanos.* TO 'conexion'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE bd_castellanos.person (
	id INT auto_increment NOT NULL,
	nombre varchar(50) NULL,
	apellido varchar(100) NULL,
	fechaNacimiento DATE NULL,
	puesto varchar(30) NULL,
	sueldo DECIMAL(10,2) NULL,
	CONSTRAINT person_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;