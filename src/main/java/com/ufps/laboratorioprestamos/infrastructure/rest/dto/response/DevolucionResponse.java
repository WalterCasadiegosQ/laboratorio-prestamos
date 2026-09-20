package com.ufps.laboratorioprestamos.infrastructure.rest.dto.response;

import java.time.LocalDate;

import com.ufps.laboratorioprestamos.domain.model.Novedad;

public record DevolucionResponse(Long id, Long prestamoId, LocalDate fechaDevolucion, Novedad novedad) {}
