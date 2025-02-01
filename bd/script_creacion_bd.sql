create database bd_Parra;

use bd_Parra;


CREATE USER 'conexion'@'%' IDENTIFIED BY 'Acceso_123!';
GRANT ALL PRIVILEGES ON bd_Parra.* TO 'conexion'@'%';
FLUSH PRIVILEGES;

CREATE TABLE person (
	id INT auto_increment NOT NULL,
	nombre varchar(50) NULL,
	apellido varchar(100) NULL,
	fecha_nacimiento DATE NULL,
	puesto varchar(30) NULL,
	sueldo DECIMAL(15,3) NULL,
	CONSTRAINT person_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;
