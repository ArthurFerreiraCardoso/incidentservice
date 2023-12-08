package com.incidentsservice.diazero.model.dto;

import com.incidentsservice.diazero.model.entity.Incident;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidentDTO {

    private Long id;
    private String name;
    private String description;

    public IncidentDTO toDTO(Incident incident) {
        IncidentDTO incidentDTO = new IncidentDTO();

        incidentDTO.setId(incident.getIncidentId());
        incidentDTO.setName(incident.getName());
        incidentDTO.setDescription(incident.getDescription());
        return incidentDTO;


    }

}
