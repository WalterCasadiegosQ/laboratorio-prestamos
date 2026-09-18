package com.ufps.laboratorioprestamos.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ufps.laboratorioprestamos.domain.model.EstadoEquipo;
import com.ufps.laboratorioprestamos.domain.model.Equipo;

public interface EquipoRepository {
    Equipo save(Equipo equipo);
    List<Equipo> findAll();
    List<Equipo> findByEstado(EstadoEquipo estado);
    Optional<Equipo> findById(Long id);
}
