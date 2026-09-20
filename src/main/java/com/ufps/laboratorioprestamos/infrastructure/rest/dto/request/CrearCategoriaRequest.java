package com.ufps.laboratorioprestamos.infrastructure.rest.dto.request;

import java.time.LocalDate;

import com.ufps.laboratorioprestamos.domain.model.Novedad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearCategoriaRequest(
        @NotBlank(message = "El nombre de la categoría es obligatorio.") String nombre,
        @NotNull(message = "Los días de préstamo son obligatorios.") @Positive(message = "Los días deben ser mayores a cero.") Integer diasPrestamo
) {}
