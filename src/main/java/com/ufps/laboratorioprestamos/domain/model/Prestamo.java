package com.ufps.laboratorioprestamos.domain.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Prestamo {
    private Long id;
    private PersonaSolicitante persona;
    private Equipo equipo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private EstadoPrestamo estado;

    public boolean estaActivo() {
        return EstadoPrestamo.ACTIVO.equals(this.estado);
    }

    public boolean estaVencido(LocalDate hoy) {
        return this.estado == EstadoPrestamo.ACTIVO && hoy.isAfter(this.fechaVencimiento);
    }

    public long diasDeAtraso(LocalDate hoy) {
        if (!estaVencido(hoy)) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(this.fechaVencimiento, hoy);
    }

    public void marcarDevuelto() {
        this.estado = EstadoPrestamo.DEVUELTO;
    }
}
