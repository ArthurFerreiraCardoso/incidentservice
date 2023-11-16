package com.incidentsservice.diazero.model;

import com.incidentsservice.diazero.model.dto.IncidentDTO;
import com.incidentsservice.diazero.model.entity.Incident;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface IncidentMapper {


    @Mapping(source = "incidentId", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    IncidentDTO toDto(Incident incident);

    @Mapping(source = "id", target = "incidentId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Incident toEntity(IncidentDTO incidentDTO);

}
