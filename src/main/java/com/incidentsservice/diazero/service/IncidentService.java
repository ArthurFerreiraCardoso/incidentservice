package com.incidentsservice.diazero.service;

import com.incidentsservice.diazero.model.dto.IncidentDTO;
import com.incidentsservice.diazero.model.dto.IncidentUpdateRequest;
import com.incidentsservice.diazero.model.entity.Incident;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncidentService {

    IncidentDTO registerIncident(IncidentDTO incidentDTO);

    IncidentUpdateRequest updateIncident(Long id, IncidentUpdateRequest updateRequest);

    void deleteIncident(Long id);

    List<IncidentDTO> getAllIncidents();

    IncidentDTO getIncidentById(Long id);

    Boolean validateIncident(IncidentDTO incidentDTO);

    List<IncidentDTO> getLast20Incidents();
}
