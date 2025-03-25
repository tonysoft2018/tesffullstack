-- 3.Crear un script de base de datos que debera de contener lo siguiente:

-- Paso 3.1: Creación de la base de datos si no existe
CREATE DATABASE IF NOT EXISTS bd_zapata;

-- Usar la base de datos creada
USE bd_zapata;

-- Paso 3.2: Creación de las tabla person
CREATE TABLE IF NOT EXISTS person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    fecha_nacimiento DATE,
    puesto VARCHAR(100),
    sueldo DECIMAL(10, 2)
);

-- Paso: 5 Creación de usuario
CREATE USER 'conexion'@'localhost' IDENTIFIED BY '123456';

-- Paso: 5.1 Asignación de privilegios
GRANT ALL PRIVILEGES ON bd_zapata.* TO 'conexion'@'localhost';

-- Asegurarse de la aplicación de privilegios
FLUSH PRIVILEGES;
