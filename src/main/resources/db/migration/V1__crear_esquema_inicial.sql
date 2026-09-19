CREATE SCHEMA IF NOT EXISTS laboratorio_prestamos;

CREATE TABLE IF NOT EXISTS laboratorio_prestamos.categoria_equipo (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    dias_prestamo INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS laboratorio_prestamos.persona (
    id BIGSERIAL PRIMARY KEY,
    nombre_completo VARCHAR(255) NOT NULL,
    documento VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS laboratorio_prestamos.equipo (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(255) NOT NULL UNIQUE,
    nombre VARCHAR(255) NOT NULL,
    categoria_id BIGINT NOT NULL,
    estado VARCHAR(255) NOT NULL CHECK (estado IN ('DISPONIBLE', 'PRESTADO', 'MANTENIMIENTO', 'BLOQUEADO')),
    CONSTRAINT fk_equipo_categoria FOREIGN KEY (categoria_id) REFERENCES laboratorio_prestamos.categoria_equipo (id)
);

CREATE TABLE IF NOT EXISTS laboratorio_prestamos.prestamo (
    id BIGSERIAL PRIMARY KEY,
    persona_id BIGINT NOT NULL,
    equipo_id BIGINT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    estado VARCHAR(255) NOT NULL CHECK (estado IN ('ACTIVO', 'DEVUELTO')),
    CONSTRAINT fk_prestamo_persona FOREIGN KEY (persona_id) REFERENCES laboratorio_prestamos.persona (id),
    CONSTRAINT fk_prestamo_equipo FOREIGN KEY (equipo_id) REFERENCES laboratorio_prestamos.equipo (id)
);

CREATE TABLE IF NOT EXISTS laboratorio_prestamos.devolucion (
    id BIGSERIAL PRIMARY KEY,
    prestamo_id BIGINT NOT NULL UNIQUE,
    fecha_devolucion DATE NOT NULL,
    novedad VARCHAR(255) CHECK (novedad IS NULL OR novedad IN ('DAÑO', 'FALTANTE')),
    CONSTRAINT fk_devolucion_prestamo FOREIGN KEY (prestamo_id) REFERENCES laboratorio_prestamos.prestamo (id)
);
