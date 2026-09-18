package com.ufps.laboratorioprestamos.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoriaEquipo {
    private Long id;
    private String nombre;
    private Integer diasPrestamo;
}
