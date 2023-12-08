package com.incidentsservice.diazero.model.dto;

import com.incidentsservice.diazero.model.entity.Incident;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidentUpdateRequest {

    private String name;
    private String description;


    public IncidentUpdateRequest entityToResponse(Incident incident) {

        IncidentUpdateRequest incidentUpdateRequest = new IncidentUpdateRequest();

        incidentUpdateRequest.name = incident.getName();
        incidentUpdateRequest.description = incident.getDescription();

        return incidentUpdateRequest;
    }
}
