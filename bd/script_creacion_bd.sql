CREATE DATABASE IF NOT EXISTS bd_Martin;

USE bd_Martin;

CREATE TABLE IF NOT EXISTS person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    fechaNacimiento DATE,
    puesto VARCHAR(255),
    sueldo DECIMAL(10, 2)
);

CREATE USER 'conexion'@'localhost' IDENTIFIED BY 'Pa$$word';
GRANT ALL PRIVILEGES ON bd_Martin.* TO 'conexion'@'localhost';
FLUSH PRIVILEGES;