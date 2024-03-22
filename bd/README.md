# Requerimientos

- MySQL instalado en su sistema. Desarrollado con Ver 8.0.31 for Win64 on x86_64 (MySQL Community Server - GPL).
- Acceso de administrador a MySQL.

# Despliegue y Ejecución

1. Clonar este repositorio:
   ```
   git clone https://github.com/usuario/repositorio.git
   ```

2. Iniciar sesión en MySQL como usuario root:
   ```
   mysql -u root -p
   ```

3. Cargar el script de creación de la base de datos:
   ```
   source ruta_del_script_creacion_bd.sql
   ```
   Ejemplo: `D:\SRC\GitHub\tonysoft2018\tesffullstack\bd\script_creacion_bd.sql`

4. Cambiar a la base de datos recién creada:
   ```
   USE bd_garcia;
   ```

5. Ejecutar la consulta para verificar los datos en la tabla person:
   ```
   SELECT * FROM person;
   ```

6. Crear un nuevo usuario para la conexión del backend y otorgarle privilegios:
   ```
   CREATE USER 'conexion'@'localhost' IDENTIFIED BY '********';
   GRANT ALL PRIVILEGES ON bd_garcia.* TO 'conexion'@'localhost';
   FLUSH PRIVILEGES;
   ```

7. Salir de MySQL:
   ```
   exit;
   ```