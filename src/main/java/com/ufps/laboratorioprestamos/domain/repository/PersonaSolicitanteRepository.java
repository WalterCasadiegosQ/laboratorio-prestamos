package com.ufps.laboratorioprestamos.domain.repository;

import java.util.List;
import java.util.Optional;

import com.ufps.laboratorioprestamos.domain.model.PersonaSolicitante;

public interface PersonaSolicitanteRepository {
    PersonaSolicitante save(PersonaSolicitante personaSolicitante);
    Optional<PersonaSolicitante> findById(Long id);
    List<PersonaSolicitante> findAll();
}
