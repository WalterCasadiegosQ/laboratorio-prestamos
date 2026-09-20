package com.ufps.laboratorioprestamos.domain.services;

import java.time.LocalDate;
import java.util.List;

import com.ufps.laboratorioprestamos.domain.model.Devolucion;
import com.ufps.laboratorioprestamos.domain.model.Novedad;

public interface DevolucionService {
    Devolucion registrarDevolucion(Long prestamoId, Novedad novedad, LocalDate fechaDevolucion);
    List<Devolucion> findAll();
    com.ufps.laboratorioprestamos.domain.model.Equipo liberarEquipo(Long equipoId);
}
