package com.ufps.laboratorioprestamos.domain.services;

import java.time.LocalDate;

import com.ufps.laboratorioprestamos.domain.model.Devolucion;
import com.ufps.laboratorioprestamos.domain.model.Novedad;

public interface DevolucionService {
    Devolucion registrarDevolucion(Long prestamoId, Novedad novedad, LocalDate fechaDevolucion);
    com.ufps.laboratorioprestamos.domain.model.Equipo liberarEquipo(Long equipoId);
}
