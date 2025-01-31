# Base de datos

## Ejecutar el comando del archivo "creation_script.sql".

### Crear un usuario llamado "conexion" con su respectivo password.

CREATE USER 'conexion'@'localhost' IDENTIFIED BY 'randompass';

### Asignar permisos al usuario creado

GRANT ALL PRIVILEGES ON conexion.* TO 'db_adame'@'localhost';