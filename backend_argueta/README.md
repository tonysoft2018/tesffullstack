# Requerimientos

* Java JDK 8 instalado.
* Maven 3.6 o superior instalado.
* MySQL instalado en su sistema. Desarrollado y probado con **MySQL Server 8.0.31**.
* Acceso de administrador a MySQL.
* Git instalado para clonar el repositorio.



# Despliegue y Ejecución

## 1. Clonar el repositorio

```bash
git clone https://github.com/tonysoft2018/tesffullstack
```

## 2. Ingresar al directorio del proyecto

```bash
cd tesffullstack
```
# Configuración Inicial (Obligatoria)

Antes de compilar o ejecutar la aplicación, debe configurar correctamente el archivo de propiedades del proyecto.
> **Importante:** Verifique que la base de datos, el usuario y la contraseña configurados existan en su servidor MySQL antes de iniciar la aplicación.
Ubique el archivo:

```text
/backend_argueta/src/main/resources/application.properties
```

Configure los siguientes valores según su entorno para la base de datos:

```properties
server.port=8080
spring.application.name=backend-test-argueta

spring.jpa.hibernate.ddl-auto=update

spring.datasource.url=jdbc:mysql://localhost:3306/bd_argueta
spring.datasource.username=conexion
spring.datasource.password=password123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.show-sql=true
```

# Compilación del Proyecto

Una vez configurado correctamente el archivo `application.properties`, compile el proyecto ejecutando:

```bash
mvn clean install
```

Si la compilación finaliza sin errores, se generará el artefacto correspondiente en la carpeta:

```text
target/
```

# Ejecución de la Aplicación

Después de una compilación exitosa, inicie la aplicación con el siguiente comando:

```bash
mvn spring-boot:run
```

O ejecutando el archivo JAR generado:

```bash
java -jar target/backend-test-argueta.jar
```

# Verificación

Una vez iniciada la aplicación, verifique que el servicio se encuentre disponible en:

```text
http://localhost:8080
```

Revise la consola de ejecución para confirmar que la conexión a la base de datos fue establecida correctamente y que la aplicación inició sin errores.
