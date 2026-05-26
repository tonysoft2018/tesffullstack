-- Ejecutar como root, con bd_joya ya creada
CREATE USER IF NOT EXISTS 'conexion'@'localhost' IDENTIFIED BY 'Joy@Bd2026!x7';
GRANT ALL PRIVILEGES ON bd_joya.* TO 'conexion'@'localhost';
FLUSH PRIVILEGES;
