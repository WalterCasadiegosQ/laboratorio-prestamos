package com.ufps.laboratorioprestamos.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.ufps.laboratorioprestamos.domain.model.EstadoEquipo;
import com.ufps.laboratorioprestamos.infrastructure.entity.EquipoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoJpaRepository extends JpaRepository<EquipoEntity, Long> {
    @Override
    @EntityGraph(attributePaths = "categoria")
    List<EquipoEntity> findAll();

    @EntityGraph(attributePaths = "categoria")
    List<EquipoEntity> findByEstado(EstadoEquipo estado);

    @Override
    @EntityGraph(attributePaths = "categoria")
    Optional<EquipoEntity> findById(Long id);
}
