# Base de datos - joya_bd

## Requerimientos

- MySQL 8.x instalado en el sistema.
- Acceso de administrador a MySQL (usuario `root`).

## Despliegue y ejecución

1. Clonar el repositorio y cambiar a la rama `joya_bd`:
   ```
   git clone https://github.com/tonysoft2018/tesffullstack.git
   cd tesffullstack
   git checkout joya_bd
   ```

2. Iniciar sesión en MySQL como usuario root:
   ```
   mysql -u root -p
   ```

3. Ejecutar el script de creación:
   ```
   source ruta_completa/bd/script_creacion_bd.sql
   ```
   Ejemplo Windows:
   ```
   source C:/Users/krism/Documents/pruebaTecnica/tesffullstack/bd/script_creacion_bd.sql
   ```

4. Verificar la base de datos y los datos de prueba:
   ```
   USE bd_joya;
   SELECT * FROM person;
   ```

5. Crear el usuario de conexión para el backend:
   ```
   CREATE USER 'conexion'@'localhost' IDENTIFIED BY 'Joy@Bd2026!x7';
   GRANT ALL PRIVILEGES ON bd_joya.* TO 'conexion'@'localhost';
   FLUSH PRIVILEGES;
   exit;
   ```

## Estructura

| Elemento | Valor |
|----------|-------|
| Base de datos | `bd_joya` |
| Tabla | `person` |
| Usuario | `conexion` |
| Password | `Joy@Bd2026!x7` |
