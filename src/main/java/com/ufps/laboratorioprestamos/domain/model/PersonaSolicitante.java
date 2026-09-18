package com.ufps.laboratorioprestamos.domain.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonaSolicitante {
    private Long id;
    private String nombreCompleto;
    private String documento;
    private String email;
}
