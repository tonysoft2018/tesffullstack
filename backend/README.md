## Backend
API desarrollada en Java 17 con Spring Boot para la gestión (CRUD) de registros de personas en una base de datos MySQL.

## Requisitos Previos
Java: JDK 17 o superior.

Maven: v3.8 o superior.

MySQL: Base de datos corriendo en el puerto 3306 (o el configurado en application.properties).

## Configuración de Base de Datos
Asegurarse de haber ejecutado el script SQL que se encuentra en la rama munoz_bd.

Verificar las credenciales en src/main/resources/application.properties.

## Instalación y Ejecución
Navegar a la carpeta del proyecto:
cd backend

Compilar el proyecto:
./mvnw clean install

Ejecutar la aplicación:
./mvnw spring-boot:run

La API estará disponible en http://localhost:8080.

## Endpoints Principales
GET /api/person: Lista todos los registros.

GET /api/person/{id}: Obtiene un registro por ID.

POST /api/person: Crea un nuevo registro.

PUT /api/person/{id}: Actualiza un registro existente.

DELETE /api/person/{id}: Elimina un registro.

## Pruebas de Funcionamiento
Se incluyen capturas de pantalla de las pruebas realizadas en Bruno (alternativa a Postman) para validar cada endpoint.

/screenshots/listar-personas.png
/screenshots/listar-una-persona.png
/screenshots/crear-una-persona.png
/screenshots/modificar-una-persona.png
/screenshots/borrar-una-persona.png