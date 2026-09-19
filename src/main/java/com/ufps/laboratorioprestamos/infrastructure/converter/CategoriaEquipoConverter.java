package com.ufps.laboratorioprestamos.infrastructure.converter;

import com.ufps.laboratorioprestamos.domain.model.CategoriaEquipo;
import com.ufps.laboratorioprestamos.infrastructure.entity.CategoriaEquipoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriaEquipoConverter {
    CategoriaEquipo toDomain(CategoriaEquipoEntity entity);
    CategoriaEquipoEntity toEntity(CategoriaEquipo domain);
}
