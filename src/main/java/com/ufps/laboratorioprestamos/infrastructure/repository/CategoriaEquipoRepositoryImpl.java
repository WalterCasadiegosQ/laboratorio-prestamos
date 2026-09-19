package com.ufps.laboratorioprestamos.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.ufps.laboratorioprestamos.domain.model.CategoriaEquipo;
import com.ufps.laboratorioprestamos.domain.repository.CategoriaEquipoRepository;
import com.ufps.laboratorioprestamos.infrastructure.entity.CategoriaEquipoEntity;
import com.ufps.laboratorioprestamos.infrastructure.converter.CategoriaEquipoConverter;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaEquipoRepositoryImpl implements CategoriaEquipoRepository {
    private final CategoriaEquipoJpaRepository jpaRepository;
    private final CategoriaEquipoConverter mapper;

    public CategoriaEquipoRepositoryImpl(CategoriaEquipoJpaRepository jpaRepository, CategoriaEquipoConverter mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoriaEquipo save(CategoriaEquipo categoriaEquipo) {
        CategoriaEquipoEntity entity = mapper.toEntity(categoriaEquipo);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public List<CategoriaEquipo> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<CategoriaEquipo> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
