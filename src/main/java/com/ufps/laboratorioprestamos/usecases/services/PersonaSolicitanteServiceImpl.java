package com.ufps.laboratorioprestamos.usecases.services;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.exception.BusinessRuleException;
import com.ufps.laboratorioprestamos.domain.model.PersonaSolicitante;
import com.ufps.laboratorioprestamos.domain.repository.PersonaSolicitanteRepository;
import com.ufps.laboratorioprestamos.domain.services.PersonaSolicitanteService;

import org.springframework.stereotype.Service;

@Service
public class PersonaSolicitanteServiceImpl implements PersonaSolicitanteService {
    private final PersonaSolicitanteRepository personaSolicitanteRepository;

    public PersonaSolicitanteServiceImpl(PersonaSolicitanteRepository personaSolicitanteRepository) {
        this.personaSolicitanteRepository = personaSolicitanteRepository;
    }

    @Override
    public PersonaSolicitante registrarPersonaSolicitante(String nombreCompleto, String documento, String email) {
        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new BusinessRuleException("El nombre completo es obligatorio.");
        }
        if (documento == null || documento.isBlank()) {
            throw new BusinessRuleException("El documento es obligatorio.");
        }
        if (email == null || email.isBlank()) {
            throw new BusinessRuleException("El email es obligatorio.");
        }

        PersonaSolicitante personaSolicitante = PersonaSolicitante.builder()
                .nombreCompleto(nombreCompleto.trim())
                .documento(documento.trim())
                .email(email.trim())
                .build();

        return personaSolicitanteRepository.save(personaSolicitante);
    }

    @Override
    public List<PersonaSolicitante> findAll() {
        return personaSolicitanteRepository.findAll();
    }
}
