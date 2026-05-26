GRANT ALL ON SCHEMA public TO conexion;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO conexion;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO conexion;

CREATE TABLE IF NOT EXISTS person (
    id                BIGSERIAL PRIMARY KEY,
    nombre            VARCHAR(100) NOT NULL,
    apellido          VARCHAR(100) NOT NULL,
    "fechaNacimiento" DATE NOT NULL,
    puesto            VARCHAR(150) NOT NULL,
    sueldo            NUMERIC(12, 2) NOT NULL
);

GRANT ALL PRIVILEGES ON TABLE person TO conexion;
GRANT USAGE, SELECT ON SEQUENCE person_id_seq TO conexion;

INSERT INTO person (nombre, apellido, "fechaNacimiento", puesto, sueldo)
VALUES
    ('Aaron', 'Orozco', '1995-06-15', 'Desarrollador Full Stack', 45000.00),
    ('Ana', 'Ruiz', '1992-08-10', 'QA', 32000.00)
ON CONFLICT DO NOTHING;
