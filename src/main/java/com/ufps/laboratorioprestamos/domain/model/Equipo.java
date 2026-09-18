package com.ufps.laboratorioprestamos.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Equipo {
    private Long id;
    private String codigo;
    private String nombre;
    private CategoriaEquipo categoria;
    private EstadoEquipo estado;

    public boolean estaDisponibleParaPrestamo() {
        return EstadoEquipo.DISPONIBLE.equals(this.estado);
    }

    public void marcarPrestado() {
        this.estado = EstadoEquipo.PRESTADO;
    }

    public void marcarEnMantenimiento() {
        this.estado = EstadoEquipo.MANTENIMIENTO;
    }

    public void marcarBloqueado() {
        this.estado = EstadoEquipo.BLOQUEADO;
    }

    public void marcarDisponible() {
        this.estado = EstadoEquipo.DISPONIBLE;
    }
}
