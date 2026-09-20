package com.ufps.laboratorioprestamos.infrastructure.rest.dto.response;

import java.time.LocalDate;

public record PrestamoVencidoResponse(
        Long prestamoId,
        Long personaId,
        String personaNombre,
        Long equipoId,
        String equipoCodigo,
        LocalDate fechaVencimiento,
        long diasDeAtraso
) {}
