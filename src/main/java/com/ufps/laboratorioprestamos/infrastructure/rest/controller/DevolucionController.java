package com.ufps.laboratorioprestamos.infrastructure.rest.controller;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.services.DevolucionService;
import com.ufps.laboratorioprestamos.infrastructure.rest.converter.RestConverter;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.request.RegistrarDevolucionRequest;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.DevolucionResponse;
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
public class DevolucionController {
    private final DevolucionService devolucionService;
    private final RestConverter mapper;

    public DevolucionController(DevolucionService devolucionService, RestConverter mapper) {
        this.devolucionService = devolucionService;
        this.mapper = mapper;
    }

    @PostMapping("/devoluciones")
    @ResponseStatus(HttpStatus.CREATED)
    public DevolucionResponse registrarDevolucion(@Valid @RequestBody RegistrarDevolucionRequest request) {
        return mapper.toResponse(devolucionService.registrarDevolucion(request.prestamoId(), request.novedad(), request.fechaDevolucion()));
    }

    @GetMapping("/devoluciones")
    public List<DevolucionResponse> consultarDevoluciones() {
        return devolucionService.findAll().stream().map(mapper::toResponse).toList();
    }
}
