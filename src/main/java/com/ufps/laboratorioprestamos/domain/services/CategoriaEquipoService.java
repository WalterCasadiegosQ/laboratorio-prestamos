package com.ufps.laboratorioprestamos.domain.services;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.model.CategoriaEquipo;

public interface CategoriaEquipoService {
    CategoriaEquipo registrarCategoria(String nombre, Integer diasPrestamo);
    List<CategoriaEquipo> findAll();
}
