package com.ufps.laboratorioprestamos.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CrearPersonaSolicitanteRequest(
        @NotBlank(message = "El nombre completo es obligatorio.") String nombreCompleto,
        @NotBlank(message = "El documento es obligatorio.") String documento,
        @NotBlank(message = "El email es obligatorio.") String email
) {}
