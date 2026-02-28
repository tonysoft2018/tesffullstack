CREATE DATABASE bd_meneses;

USE bd_meneses;

CREATE TABLE person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    puesto VARCHAR(100) NOT NULL,
    sueldo DECIMAL(10,2) NOT NULL
);

CREATE USER 'conexion'@'localhost' IDENTIFIED BY 'qwerty$1';

GRANT ALL PRIVILEGES ON bd_meneses.* TO 'conexion'@'localhost';

FLUSH PRIVILEGES;