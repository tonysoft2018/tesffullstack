
USE bd_argueta;
CREATE TABLE IF NOT EXISTS `bd_argueta`.`person`(
    id BIGINT UNSIGNED AUTO_INCREMENT primary key,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    fechaNacimiento DATE,
    puesto VARCHAR(255),
    sueldo DECIMAL(10, 2)
);