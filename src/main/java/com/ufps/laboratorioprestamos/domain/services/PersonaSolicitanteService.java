package com.ufps.laboratorioprestamos.domain.services;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.model.PersonaSolicitante;

public interface PersonaSolicitanteService {
    PersonaSolicitante registrarPersonaSolicitante(String nombreCompleto, String documento, String email);
    List<PersonaSolicitante> findAll();
}
