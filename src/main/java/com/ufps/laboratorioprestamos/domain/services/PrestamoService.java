package com.ufps.laboratorioprestamos.domain.services;

import java.time.LocalDate;
import java.util.List;

import com.ufps.laboratorioprestamos.domain.model.Prestamo;
import com.ufps.laboratorioprestamos.domain.model.PrestamoVencidoDto;

public interface PrestamoService {
    Prestamo registrarPrestamo(Long personaId, Long equipoId, LocalDate fechaPrestamo);
    List<Prestamo> findAll();
    List<PrestamoVencidoDto> consultarPrestamosVencidos();
}
