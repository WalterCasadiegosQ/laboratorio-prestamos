package com.ufps.laboratorioprestamos.usecases.services;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.exception.BusinessRuleException;
import com.ufps.laboratorioprestamos.domain.model.CategoriaEquipo;
import com.ufps.laboratorioprestamos.domain.repository.CategoriaEquipoRepository;
import com.ufps.laboratorioprestamos.domain.services.CategoriaEquipoService;

import org.springframework.stereotype.Service;

@Service
public class CategoriaEquipoServiceImpl implements CategoriaEquipoService {
    private final CategoriaEquipoRepository categoriaEquipoRepository;

    public CategoriaEquipoServiceImpl(CategoriaEquipoRepository categoriaEquipoRepository) {
        this.categoriaEquipoRepository = categoriaEquipoRepository;
    }

    @Override
    public CategoriaEquipo registrarCategoria(String nombre, Integer diasPrestamo) {
        if (nombre == null || nombre.isBlank()) {
            throw new BusinessRuleException("El nombre de la categoría es obligatorio.");
        }
        if (diasPrestamo == null || diasPrestamo <= 0) {
            throw new BusinessRuleException("Los días de préstamo deben ser mayores que cero.");
        }

        CategoriaEquipo categoriaEquipo = CategoriaEquipo.builder()
                .nombre(nombre.trim())
                .diasPrestamo(diasPrestamo)
                .build();

        return categoriaEquipoRepository.save(categoriaEquipo);
    }

    @Override
    public List<CategoriaEquipo> findAll() {
        return categoriaEquipoRepository.findAll();
    }
}
