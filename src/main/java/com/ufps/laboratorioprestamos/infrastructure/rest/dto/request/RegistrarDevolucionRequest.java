package com.ufps.laboratorioprestamos.infrastructure.rest.dto.request;

import java.time.LocalDate;

import com.ufps.laboratorioprestamos.domain.model.Novedad;
import jakarta.validation.constraints.NotNull;

public record RegistrarDevolucionRequest(
        @NotNull(message = "El préstamo es obligatorio.") Long prestamoId,
        Novedad novedad,
        @NotNull(message = "La fecha de devolución es obligatoria.") LocalDate fechaDevolucion
) {}
