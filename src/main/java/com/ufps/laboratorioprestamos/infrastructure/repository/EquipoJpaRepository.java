package com.ufps.laboratorioprestamos.infrastructure.repository;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.model.EstadoEquipo;
import com.ufps.laboratorioprestamos.infrastructure.entity.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoJpaRepository extends JpaRepository<EquipoEntity, Long> {
    List<EquipoEntity> findByEstado(EstadoEquipo estado);
}
