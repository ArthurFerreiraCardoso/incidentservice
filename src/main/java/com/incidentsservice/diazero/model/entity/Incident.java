package com.incidentsservice.diazero.model.entity;

import com.incidentsservice.diazero.model.dto.IncidentDTO;
import com.incidentsservice.diazero.model.dto.IncidentUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long incidentId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at", length = 100, nullable = false)
    private final LocalDate createdAt = LocalDate.now();
    @Column(name = "updated", nullable = false)
    private final LocalDate updatedAt = LocalDate.now();
    @Column(name = "closed_at", length = 100, nullable = false)
    private final LocalDate closedAt = LocalDate.now();

    public Incident toEntity(IncidentDTO incidentDTO) {
        Incident incident = new Incident();

        incident.incidentId = incidentDTO.getId();
        incident.name = incidentDTO.getName();
        incident.description = incidentDTO.getDescription();
        return incident;
    }

    public IncidentDTO toDto(Incident incident) {
        IncidentDTO incidentDTO = new IncidentDTO(incident.incidentId,
                incident.name, incident.description);
        return incidentDTO;
    }


    public Incident requestToEntity(IncidentUpdateRequest updateRequest) {

        Incident incident = new Incident();

        incident.name = updateRequest.getName();
        incident.description = updateRequest.getDescription();

        return incident;
    }

}

