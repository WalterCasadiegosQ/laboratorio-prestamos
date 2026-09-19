package com.ufps.laboratorioprestamos.infrastructure.repository;

import java.util.List;

import com.ufps.laboratorioprestamos.domain.model.Devolucion;
import com.ufps.laboratorioprestamos.domain.repository.DevolucionRepository;
import com.ufps.laboratorioprestamos.infrastructure.converter.DevolucionConverter;
import com.ufps.laboratorioprestamos.infrastructure.entity.DevolucionEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DevolucionRepositoryImpl implements DevolucionRepository {
    private final DevolucionJpaRepository jpaRepository;
    private final DevolucionConverter mapper;

    public DevolucionRepositoryImpl(DevolucionJpaRepository jpaRepository, DevolucionConverter mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Devolucion save(Devolucion devolucion) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(devolucion)));
    }

    @Override
    public List<Devolucion> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
}
