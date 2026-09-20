package com.ufps.laboratorioprestamos.infrastructure.rest.dto.response;

import java.time.LocalDate;

import com.ufps.laboratorioprestamos.domain.model.EstadoPrestamo;

public record PrestamoResponse(
        Long id,
        Long personaId,
        String personaNombre,
        Long equipoId,
        String equipoCodigo,
        LocalDate fechaPrestamo,
        LocalDate fechaVencimiento,
        EstadoPrestamo estado
) {}
