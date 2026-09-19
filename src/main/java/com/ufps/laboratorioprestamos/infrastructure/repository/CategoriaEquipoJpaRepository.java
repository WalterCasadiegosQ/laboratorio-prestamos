package com.ufps.laboratorioprestamos.infrastructure.repository;

import java.util.List;

import com.ufps.laboratorioprestamos.infrastructure.entity.CategoriaEquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaEquipoJpaRepository extends JpaRepository<CategoriaEquipoEntity, Long> {
}
