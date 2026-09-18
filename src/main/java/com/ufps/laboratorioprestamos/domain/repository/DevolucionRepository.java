package com.ufps.laboratorioprestamos.domain.repository;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.model.Devolucion;

public interface DevolucionRepository {
    Devolucion save(Devolucion devolucion);
    List<Devolucion> findAll();
}
