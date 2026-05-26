# PersonalHub · Frontend Orozco

Aplicación web desarrollada con **Angular 20** para la gestión de personal. Consume la API REST del backend conectada a la base de datos **`orozco_bd`**.

**Autor:** Aaron Orozco  
**Prueba técnica:** Stefanini

---

## Descripción

Frontend SPA que permite administrar el directorio de colaboradores con operaciones CRUD completas:

- Listar personal registrado
- Crear nuevos colaboradores
- Editar registros existentes
- Eliminar registros con confirmación

La interfaz utiliza un diseño con **sidebar lateral**, **tarjetas en grid** y tipografía personalizada, diferenciado de otras implementaciones del mismo requerimiento.

---

## Tecnologías

| Tecnología | Versión |
|------------|---------|
| Angular | 20.x |
| Angular Material | 20.x |
| TypeScript | 5.9.x |
| RxJS | 7.8.x |
| Node.js | 22.12.0 (requerido) |

---

## Requisitos previos

1. **Node.js 22.12.0** o superior compatible (`>= 20.19` o `>= 22.12`)
2. **fnm** (Fast Node Manager), instalable sin permisos de administrador
3. **Backend** activo en `http://localhost:8080`
4. Base de datos **`orozco_bd`** configurada en el backend

### Instalar fnm (solo una vez, sin admin)

```powershell
winget install Schniz.fnm --scope user
```

El proyecto incluye el script `scripts/run-with-node.ps1` y el archivo `.node-version` para ejecutar Angular con la versión correcta de Node, aunque el sistema tenga una versión antigua instalada.

---

## Instalación

```powershell
cd orozco_frontend
npm install
```

---

## Ejecución

### Servidor de desarrollo

```powershell
npm start
```

La aplicación quedará disponible en:

**http://localhost:4200/**

### Compilar para producción

```powershell
npm run build
```

Los artefactos se generan en la carpeta `dist/`.

### Ejecutar pruebas unitarias

```powershell
npm test
```

---

## Rutas de la aplicación

| Ruta | Componente | Descripción |
|------|------------|-------------|
| `/` | PersonListComponent | Directorio de colaboradores |
| `/nuevo` | PersonFormComponent | Formulario de alta |
| `/editar/:id` | PersonFormComponent | Formulario de edición |

---

## API consumida

**Base URL:** `http://localhost:8080/api/persons`

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/findAll` | Listado completo |
| GET | `/findById/{id}` | Consulta por identificador |
| POST | `/create` | Registro de persona |
| PUT | `/update/{id}` | Actualización |
| DELETE | `/delete/{id}` | Eliminación |

### Modelo de persona

```json
{
  "id": 1,
  "nombre": "Aaron",
  "apellido": "Orozco",
  "fechaNacimiento": "1995-06-15",
  "puesto": "Desarrollador Full Stack",
  "sueldo": 4500000
}
```

### Respuesta estándar de la API

```json
{
  "status": true,
  "msg": "Operación exitosa",
  "data": []
}
```

---

## Estructura del proyecto

```
src/
├── app/
│   ├── models/           # Interfaces Person y ApiResponse
│   ├── pages/
│   │   ├── person-list/  # Listado en tarjetas
│   │   └── person-form/  # Formulario crear/editar
│   ├── services/         # PersonService (HTTP)
│   └── shared/           # Diálogo de confirmación
├── index.html
└── styles.scss           # Tema global y variables CSS
scripts/
└── run-with-node.ps1     # Ejecuta comandos con Node 22 via fnm
.node-version             # Versión de Node requerida
```

---

## Solución de problemas

### Error: `Node.js version v16.x detected`

El sistema tiene una versión antigua de Node en el PATH. Ejecuta siempre con:

```powershell
npm start
```

No uses `ng serve` directamente. El script del proyecto selecciona Node 22 automáticamente.

### Error: `No se pudo conectar con el servidor`

Verifica que el backend esté corriendo en el puerto **8080** y que la base **`orozco_bd`** esté disponible.

### Puerto 4200 ocupado

Detén el proceso anterior o cierra la terminal donde quedó corriendo `npm start`.

---

## Autor

**Aaron Orozco** — Prueba técnica Stefanini
