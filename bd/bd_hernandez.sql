CREATE DATABASE IF NOT EXISTS bd_hernandez;

USE bd_ramirez;

CREATE TABLE IF NOT EXISTS person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    puesto VARCHAR(100),
    sueldo DOUBLE(10, 2)
);

CREATE USER 'conexion'@'localhost' IDENTIFIED BY 'conexion2024@';
GRANT ALL PRIVILEGES ON bd_hernandez.* TO 'conexion'@'localhost';
FLUSH PRIVILEGES;