package com.ufps.laboratorioprestamos.domain.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PrestamoVencidoDto {
    private Long prestamoId;
    private Long personaId;
    private String personaNombre;
    private Long equipoId;
    private String equipoCodigo;
    private LocalDate fechaVencimiento;
    private long diasDeAtraso;
}
