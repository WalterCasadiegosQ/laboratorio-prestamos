package com.ufps.laboratorioprestamos.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearEquipoRequest(
        @NotBlank(message = "El código del equipo es obligatorio.") String codigo,
        @NotBlank(message = "El nombre del equipo es obligatorio.") String nombre,
        @NotNull(message = "La categoría es obligatoria.") Long categoriaId
) {}
