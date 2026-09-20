package com.ufps.laboratorioprestamos.infrastructure.repository;

import java.util.List;

import com.ufps.laboratorioprestamos.infrastructure.entity.DevolucionEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevolucionJpaRepository extends JpaRepository<DevolucionEntity, Long> {
    @Override
    @EntityGraph(attributePaths = {
            "prestamo",
            "prestamo.persona",
            "prestamo.equipo",
            "prestamo.equipo.categoria"
    })
    List<DevolucionEntity> findAll();
}
