# Requerimientos

- MySQL instalado en su sistema. Desarrollado con Ver 8.0.31 for Win64 on x86_64 (MySQL Community Server - GPL).
- Acceso de administrador a MySQL.

# Despliegue y Ejecución

1. Clonar este repositorio:
   ```bash
   git clone https://github.com/tonysoft2018/tesffullstack.git
   ```
2. Cambiarse a rama `argueta_bd`:
   ```bash
   git checkout argueta_bd
   ```

3. Iniciar sesión en MySQL como usuario root:
   ```bash
   mysql -u root -p
   ```

4. Cargar el script de creación de la base de datos:
   ```bash
   source 1_CREATE_DATABASE.sql
   ```
   Ubicación: `D:\SRC\GitHub\tonysoft2018\tesffullstack\Script\DDL\1_CREATE_DATABASE.sql`

5. Cargar el script de creación de usuario completo de la base de datos:
   ```bash
   source 2_USER_CONNECTION.sql
   ```
   Ubicación: `D:\SRC\GitHub\tonysoft2018\tesffullstack\Script\DDL\2_USER_CONNECTION.sql`

6. Cargar el script de creación de la tabla en la base de datos:
   ```bash
   source 3_CREATE_TABLE_PERSON.sql
   ```
   Ubicación: `D:\SRC\GitHub\tonysoft2018\tesffullstack\Script\DDL\3_CREATE_TABLE_PERSON.sql`

4. Cambiar a la base de datos recién creada:
   ```bash
   USE bd_argueta;
   ```

5. Ejecutar la consulta para verificar los datos en la tabla person:
   ```bash
   SELECT * FROM person;
   ```

7. Salir de MySQL:
   ```bash
   exit;
   ```