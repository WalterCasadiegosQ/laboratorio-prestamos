package com.ufps.laboratorioprestamos.infrastructure.rest.converter;

import com.ufps.laboratorioprestamos.domain.model.CategoriaEquipo;
import com.ufps.laboratorioprestamos.domain.model.Devolucion;
import com.ufps.laboratorioprestamos.domain.model.Equipo;
import com.ufps.laboratorioprestamos.domain.model.PersonaSolicitante;
import com.ufps.laboratorioprestamos.domain.model.Prestamo;
import com.ufps.laboratorioprestamos.domain.model.PrestamoVencidoDto;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.CategoriaEquipoResponse;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.DevolucionResponse;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.EquipoResponse;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.PersonaSolicitanteResponse;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.PrestamoResponse;
import com.ufps.laboratorioprestamos.infrastructure.rest.dto.response.PrestamoVencidoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestConverter {
    CategoriaEquipoResponse toResponse(CategoriaEquipo categoriaEquipo);
    PersonaSolicitanteResponse toResponse(PersonaSolicitante personaSolicitante);

    @Mapping(target = "categoriaId", source = "categoria.id")
    @Mapping(target = "categoriaNombre", source = "categoria.nombre")
    @Mapping(target = "estado", source = "estado")
    EquipoResponse toResponse(Equipo equipo);

    @Mapping(target = "personaId", source = "persona.id")
    @Mapping(target = "personaNombre", source = "persona.nombreCompleto")
    @Mapping(target = "equipoId", source = "equipo.id")
    @Mapping(target = "equipoCodigo", source = "equipo.codigo")
    PrestamoResponse toResponse(Prestamo prestamo);

    @Mapping(target = "prestamoId", source = "prestamo.id")
    DevolucionResponse toResponse(Devolucion devolucion);

    @Mapping(target = "prestamoId", source = "prestamoId")
    @Mapping(target = "personaId", source = "personaId")
    @Mapping(target = "personaNombre", source = "personaNombre")
    @Mapping(target = "equipoId", source = "equipoId")
    @Mapping(target = "equipoCodigo", source = "equipoCodigo")
    PrestamoVencidoResponse toResponse(PrestamoVencidoDto prestamoVencidoDto);
}
