package com.ufps.laboratorioprestamos.domain.repository;

import java.util.List;
import java.util.Optional;

import com.ufps.laboratorioprestamos.domain.model.CategoriaEquipo;

public interface CategoriaEquipoRepository {
    CategoriaEquipo save(CategoriaEquipo categoriaEquipo);
    List<CategoriaEquipo> findAll();
    Optional<CategoriaEquipo> findById(Long id);
}
