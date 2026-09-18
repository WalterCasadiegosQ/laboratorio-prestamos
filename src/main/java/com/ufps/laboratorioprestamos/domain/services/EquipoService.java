package com.ufps.laboratorioprestamos.domain.services;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.model.Equipo;

public interface EquipoService {
    Equipo registrarEquipo(String codigo, String nombre, Long categoriaId);
    Equipo actualizarEstado(Long equipoId, com.ufps.laboratorioprestamos.domain.model.EstadoEquipo estado);
    Equipo marcarEnMantenimiento(Long equipoId);
    List<Equipo> findAll();
    List<Equipo> consultarEquiposDisponibles();
    Equipo liberarEquipo(Long equipoId);
}
