# API REST — `test-full-stack-orozco`

**Autor:** Aaron Orozco  
**Stack:** Java 17 · Spring Boot 3.2 · Spring Data JPA · Maven · PostgreSQL 17  
**Base URL:** `http://localhost:8080`

## Contexto

API REST para gestión de personas (CRUD) sobre PostgreSQL.  
El proyecto mantiene un contrato de respuesta uniforme y actualmente usa una arquitectura por capas:
- `controller` para exponer endpoints
- `service` / `serviceImpl` para lógica de negocio
- `repository` para acceso a datos

## Contrato de respuesta

Todas las operaciones responden con:

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `status` | boolean | Resultado de la operación |
| `msg` | string | Mensaje descriptivo |
| `data` | array \| null | Registros devueltos o `null` en eliminación/error |

Ejemplo:

```json
{
  "status": true,
  "msg": "Persona encontrada",
  "data": [
    {
      "id": 1,
      "nombre": "Aaron",
      "apellido": "Orozco",
      "fechaNacimiento": "1995-06-15",
      "puesto": "Desarrollador Full Stack",
      "sueldo": 45000.00
    }
  ]
}
```

## Endpoints vigentes

| Método | Ruta | Descripción |
|--------|------|-------------|
| `GET` | `/api/persons/findAll` | Listado completo |
| `GET` | `/api/persons/findById/{id}` | Consulta por identificador |
| `POST` | `/api/persons/create` | Registro de persona |
| `PUT` | `/api/persons/update/{id}` | Actualización |
| `DELETE` | `/api/persons/delete/{id}` | Eliminación |

## Persistencia y configuración

`src/main/resources/application.properties`:

| Parámetro | Valor |
|-----------|-------|
| JDBC URL | `jdbc:postgresql://localhost:5432/orozco_bd` |
| Usuario | `conexion` |
| Driver | `org.postgresql.Driver` |
| Dialecto | `org.hibernate.dialect.PostgreSQLDialect` |
| Hibernate DDL | `none` |

La entidad `Person` mapea la tabla `person`, incluyendo la columna `"fechaNacimiento"` (respetando mayúsculas/minúsculas de PostgreSQL).

## Estructura del proyecto

```
src/
├── main/java/com/orozco/testfullstack/
│   ├── controller/PersonController.java
│   ├── service/PersonService.java
│   ├── service/impl/PersonServiceImpl.java
│   ├── repository/PersonRepository.java
│   └── model/Person.java, ApiResponse.java
└── test/java/com/orozco/testfullstack/
    ├── controller/PersonControllerTest.java
    ├── service/impl/PersonServiceImplTest.java
    └── model/ApiResponseTest.java
```

## Pruebas

| Clase | Cobertura |
|-------|-----------|
| `PersonControllerTest` | Contrato HTTP del controlador con `@WebMvcTest` |
| `PersonServiceImplTest` | Lógica de negocio con `Mockito` |
| `ApiResponseTest` | Fábrica de respuestas `ok` / `error` |

Ejecución:

```bash
mvnw.cmd test
```

## Evidencia (Postman)

### GET por id
`GET /api/persons/findById/1`

![GET por id](assets/c__Users_aogonzalez1_AppData_Roaming_Cursor_User_workspaceStorage_b26774c5ed8227ee55a9c7cb8a639d7a_images_image-c876ff42-9705-4829-bbd5-e7841ae6fdb5.png)

### GET listado
`GET /api/persons/findAll`

![GET listado](assets/c__Users_aogonzalez1_AppData_Roaming_Cursor_User_workspaceStorage_b26774c5ed8227ee55a9c7cb8a639d7a_images_image-5de1c816-9a4f-4f03-84de-07337a5e72d6.png)

### POST crear
`POST /api/persons/create`

![POST crear](assets/c__Users_aogonzalez1_AppData_Roaming_Cursor_User_workspaceStorage_b26774c5ed8227ee55a9c7cb8a639d7a_images_image-fb721d59-372b-4855-a22f-fe3f85cf31e9.png)

### PUT actualizar
`PUT /api/persons/update/4`

![PUT actualizar](assets/c__Users_aogonzalez1_AppData_Roaming_Cursor_User_workspaceStorage_b26774c5ed8227ee55a9c7cb8a639d7a_images_image-c5e82145-52c8-4753-b0c8-1932e17b5487.png)

### DELETE eliminar
`DELETE /api/persons/delete/4`

![DELETE eliminar](assets/c__Users_aogonzalez1_AppData_Roaming_Cursor_User_workspaceStorage_b26774c5ed8227ee55a9c7cb8a639d7a_images_image-6c883a65-a388-4669-a7d2-3ae2b626cf91.png)
