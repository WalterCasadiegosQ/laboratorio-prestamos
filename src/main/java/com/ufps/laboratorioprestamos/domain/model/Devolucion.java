package com.ufps.laboratorioprestamos.domain.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Devolucion {
    private Long id;
    private Prestamo prestamo;
    private LocalDate fechaDevolucion;
    private Novedad novedad;
}
