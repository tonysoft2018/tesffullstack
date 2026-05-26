CREATE DATABASE IF NOT EXISTS bd_joya;

USE bd_joya;

CREATE TABLE IF NOT EXISTS person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    fechaNacimiento DATE,
    puesto VARCHAR(255),
    sueldo DECIMAL(10, 2)
);

INSERT INTO person (nombre, apellido, fechaNacimiento, puesto, sueldo) VALUES
('Cristian', 'Joya', '1995-06-15', 'Desarrollador Full Stack', 45000.00),
('Maria', 'Lopez', '1990-03-22', 'Analista de datos', 38000.50);
