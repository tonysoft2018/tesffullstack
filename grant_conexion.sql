-- Ejecutar en DBeaver conectado como postgres, base de datos postgres

GRANT ALL PRIVILEGES ON TABLE person TO conexion;
GRANT USAGE, SELECT ON SEQUENCE person_id_seq TO conexion;

-- Si tambien usas orozco_bd, conectate a esa base y ejecuta lo mismo ahi.
