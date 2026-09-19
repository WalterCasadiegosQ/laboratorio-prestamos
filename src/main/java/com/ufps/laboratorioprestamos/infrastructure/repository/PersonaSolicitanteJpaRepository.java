package com.ufps.laboratorioprestamos.infrastructure.repository;

import com.ufps.laboratorioprestamos.infrastructure.entity.PersonaSolicitanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaSolicitanteJpaRepository extends JpaRepository<PersonaSolicitanteEntity, Long> {
}
