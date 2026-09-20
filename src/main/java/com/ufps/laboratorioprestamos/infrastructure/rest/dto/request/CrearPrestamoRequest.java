package com.ufps.laboratorioprestamos.infrastructure.rest.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record CrearPrestamoRequest(
        @NotNull(message = "La persona solicitante es obligatoria.") Long personaId,
        @NotNull(message = "El equipo es obligatorio.") Long equipoId,
        @NotNull(message = "La fecha de préstamo es obligatoria.") LocalDate fechaPrestamo
) {}
