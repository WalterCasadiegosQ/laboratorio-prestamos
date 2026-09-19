package com.ufps.laboratorioprestamos.infrastructure.converter;

import com.ufps.laboratorioprestamos.domain.model.Equipo;
import com.ufps.laboratorioprestamos.infrastructure.entity.EquipoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = CategoriaEquipoConverter.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipoConverter {
    @Mapping(target = "categoria", source = "categoria")
    Equipo toDomain(EquipoEntity entity);

    @Mapping(target = "categoria", source = "categoria")
    EquipoEntity toEntity(Equipo domain);
}
