
CREATE DATABASE IF NOT EXISTS bd_malpica;

USE bd_malpica;

CREATE TABLE person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    puesto VARCHAR(100) NOT NULL,
    sueldo DECIMAL(10,2) NOT NULL
);


CREATE USER 'conexionTest'@'localhost'
IDENTIFIED BY 'ConexionTest123456*';


GRANT ALL PRIVILEGES
ON bd_malpica.*
TO 'conexionTest'@'localhost';

FLUSH PRIVILEGES;