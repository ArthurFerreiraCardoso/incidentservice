package com.incidentsservice.diazero.service;

import com.incidentsservice.diazero.model.dto.IncidentDTO;
import com.incidentsservice.diazero.model.dto.IncidentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncidentService {

    IncidentDTO registerIncident(IncidentDTO incidentDTO);

    IncidentDTO updateIncident(Long id, IncidentUpdateRequest updateRequest);

    void deleteIncident(Long id);

    List<IncidentDTO> getAllIncidents();

    IncidentDTO getIncidentById(Long id);

    IncidentDTO validateIncident(IncidentDTO incident);

    List<IncidentDTO> getLast20Incidents();
}
