package com.ufps.laboratorioprestamos.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.ufps.laboratorioprestamos.domain.model.Equipo;
import com.ufps.laboratorioprestamos.domain.model.EstadoEquipo;
import com.ufps.laboratorioprestamos.domain.repository.EquipoRepository;
import com.ufps.laboratorioprestamos.infrastructure.entity.EquipoEntity;
import com.ufps.laboratorioprestamos.infrastructure.converter.EquipoConverter;
import org.springframework.stereotype.Repository;

@Repository
public class EquipoRepositoryImpl implements EquipoRepository {
    private final EquipoJpaRepository jpaRepository;
    private final EquipoConverter mapper;

    public EquipoRepositoryImpl(EquipoJpaRepository jpaRepository, EquipoConverter mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Equipo save(Equipo equipo) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(equipo)));
    }

    @Override
    public List<Equipo> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Equipo> findByEstado(EstadoEquipo estado) {
        return jpaRepository.findByEstado(estado).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Equipo> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
