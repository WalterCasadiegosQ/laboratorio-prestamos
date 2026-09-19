package com.ufps.laboratorioprestamos.infrastructure.repository;

import com.ufps.laboratorioprestamos.infrastructure.entity.DevolucionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevolucionJpaRepository extends JpaRepository<DevolucionEntity, Long> {
}
