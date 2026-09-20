package com.ufps.laboratorioprestamos.infrastructure.rest.dto.response;

import com.ufps.laboratorioprestamos.domain.model.EstadoEquipo;

public record EquipoResponse(
        Long id,
        String codigo,
        String nombre,
        Long categoriaId,
        String categoriaNombre,
        EstadoEquipo estado
) {}
