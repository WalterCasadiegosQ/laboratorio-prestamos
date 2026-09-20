package com.ufps.laboratorioprestamos.infrastructure.rest.controller;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.services.EquipoService;
import com.ufps.laboratorioprestamos.infrastructure.rest.converter.RestConverter;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.request.CrearEquipoRequest;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.EquipoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EquipoController {
    private final EquipoService equipoService;

    private final RestConverter mapper;

    public EquipoController(EquipoService equipoService, RestConverter mapper) {
        this.equipoService = equipoService;
        this.mapper = mapper;
    }

    @PostMapping("/equipos")
    @ResponseStatus(HttpStatus.CREATED)
    public EquipoResponse registrarEquipo(@Valid @RequestBody CrearEquipoRequest request) {
        return mapper.toResponse(equipoService.registrarEquipo(request.codigo(), request.nombre(), request.categoriaId()));
    }

    @GetMapping("/equipos")
    public List<EquipoResponse> consultarEquipos() {
        return equipoService.findAll().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/equipos/disponibles")
    public List<EquipoResponse> consultarEquiposDisponibles() {
        return equipoService.consultarEquiposDisponibles().stream().map(mapper::toResponse).toList();
    }

    @PostMapping("/equipos/{equipoId}/liberar")
    public EquipoResponse liberarEquipo(@PathVariable Long equipoId) {
        return mapper.toResponse(equipoService.liberarEquipo(equipoId));
    }
}
