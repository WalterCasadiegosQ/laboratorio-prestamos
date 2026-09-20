package com.ufps.laboratorioprestamos.infrastructure.rest.controller;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.services.PrestamoService;
import com.ufps.laboratorioprestamos.infrastructure.rest.converter.RestConverter;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.request.CrearPrestamoRequest;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.PrestamoResponse;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.PrestamoVencidoResponse;
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
public class PrestamoController {
    private final PrestamoService prestamoService;
    private final RestConverter mapper;

    public PrestamoController(PrestamoService prestamoService, RestConverter mapper) {
        this.prestamoService = prestamoService;
        this.mapper = mapper;
    }

    @PostMapping("/prestamos")
    @ResponseStatus(HttpStatus.CREATED)
    public PrestamoResponse registrarPrestamo(@Valid @RequestBody CrearPrestamoRequest request) {
        return mapper.toResponse(prestamoService.registrarPrestamo(request.personaId(), request.equipoId(), request.fechaPrestamo()));
    }

    @GetMapping("/prestamos")
    public List<PrestamoResponse> consultarPrestamos() {
        return prestamoService.findAll().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/prestamos-vencidos")
    public List<PrestamoVencidoResponse> consultarPrestamosVencidos() {
        return prestamoService.consultarPrestamosVencidos().stream().map(mapper::toResponse).toList();
    }
}
