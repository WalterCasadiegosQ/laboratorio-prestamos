package com.ufps.laboratorioprestamos.infrastructure.rest.controller;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.services.CategoriaEquipoService;
import com.ufps.laboratorioprestamos.infrastructure.rest.converter.RestConverter;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.request.CrearCategoriaRequest;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.CategoriaEquipoResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoriaEquipoController {
    private final CategoriaEquipoService categoriaEquipoService;
    private final RestConverter mapper;

    public CategoriaEquipoController(CategoriaEquipoService categoriaEquipoService, RestConverter mapper) {
        this.categoriaEquipoService = categoriaEquipoService;
        this.mapper = mapper;
    }

    @PostMapping("/categorias")
    public CategoriaEquipoResponse registrarCategoria(@Valid @RequestBody CrearCategoriaRequest request) {
        return mapper.toResponse(categoriaEquipoService.registrarCategoria(request.nombre(), request.diasPrestamo()));
    }

    @GetMapping("/categorias")
    public List<CategoriaEquipoResponse> consultarCategorias() {
        return categoriaEquipoService.findAll().stream().map(mapper::toResponse).toList();
    }
}
