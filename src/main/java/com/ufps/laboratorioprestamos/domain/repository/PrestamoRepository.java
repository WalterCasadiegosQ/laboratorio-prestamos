package com.ufps.laboratorioprestamos.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ufps.laboratorioprestamos.domain.model.Prestamo;

public interface PrestamoRepository {
    Prestamo save(Prestamo prestamo);
    List<Prestamo> findAll();
    Optional<Prestamo> findById(Long id);
    List<Prestamo> findActiveByPersonId(Long personaId);
    List<Prestamo> findActiveOverdue(LocalDate hoy);
}
