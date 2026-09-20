package com.ufps.laboratorioprestamos.infrastructure.rest.controller;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.services.PersonaSolicitanteService;
import com.ufps.laboratorioprestamos.infrastructure.rest.converter.RestConverter;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.request.CrearPersonaSolicitanteRequest;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.PersonaSolicitanteResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonaSolicitanteController {
    private final PersonaSolicitanteService personaSolicitanteService;
    private final RestConverter mapper;

    public PersonaSolicitanteController(PersonaSolicitanteService personaSolicitanteService, RestConverter mapper) {
        this.personaSolicitanteService = personaSolicitanteService;
        this.mapper = mapper;
    }

    @PostMapping("/personas-solicitantes")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonaSolicitanteResponse registrarPersonaSolicitante(@Valid @RequestBody CrearPersonaSolicitanteRequest request) {
        return mapper.toResponse(personaSolicitanteService.registrarPersonaSolicitante(request.nombreCompleto(), request.documento(), request.email()));
    }

    @GetMapping("/personas-solicitantes")
    public List<PersonaSolicitanteResponse> consultarPersonasSolicitantes() {
        return personaSolicitanteService.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
