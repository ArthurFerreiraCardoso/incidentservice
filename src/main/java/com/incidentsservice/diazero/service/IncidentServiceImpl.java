package com.incidentsservice.diazero.service;

import com.incidentsservice.diazero.exception.IncidentException;
import com.incidentsservice.diazero.model.dto.IncidentDTO;
import com.incidentsservice.diazero.model.dto.IncidentUpdateRequest;
import com.incidentsservice.diazero.model.entity.Incident;
import com.incidentsservice.diazero.repository.IncidentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentServiceImpl(IncidentRepository incidentReposity) {
        this.incidentRepository = incidentReposity;
    }


    @Override
    public IncidentDTO registerIncident(IncidentDTO incidentDTO) {

        log.info("Validate incident");
        if (validateIncident(incidentDTO)) {
            Incident incident = new Incident().toEntity(incidentDTO);

            incidentRepository.save(incident);

            return incidentDTO.toDTO(incident);

        } else if (!validateIncident(incidentDTO)) {
            throw new IncidentException("Not allowed to create incident", HttpStatus.BAD_REQUEST);

        }
        return incidentDTO;
    }


    @Override
    public IncidentUpdateRequest updateIncident(Long id, IncidentUpdateRequest updateRequest) {

        //Find the incident by ID
        try {
            incidentRepository.findById(id);

            Incident incident = new Incident().requestToEntity(updateRequest);
            incident.setName(updateRequest.getName());
            incident.setDescription(updateRequest.getDescription());

            incidentRepository.save(incident);
            return updateRequest.entityToResponse(incident);

        } catch (IncidentException e) {
            e.exceptionMethodImpl("Incident not found", HttpStatus.NOT_FOUND);
        }

        return updateRequest;
    }


    @Override
    public Boolean validateIncident(IncidentDTO incidentDTO) {
        try {
            Incident incident = new Incident().toEntity(incidentDTO);
            String name = incident.getName().toUpperCase();

            if (incidentRepository.existsByName(name)) {
                return true;
            } else {
                return false;
            }
        } catch (IncidentException e) {
            e.exceptionMethodImpl("Not allowed to create incident", HttpStatus.BAD_REQUEST);
            return false;
        }
    }


    @Override
    public void deleteIncident(Long id) {
        //verify if exists

        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentException("Incident Not found", HttpStatus.NOT_FOUND));

            incidentRepository.delete(incident);
    }


    @Override
    public List<IncidentDTO> getAllIncidents() {
        List<Incident> list = incidentRepository.findAll();
        List<IncidentDTO> incidentDTOList = new ArrayList<>();

        for (Incident incident : list) {
            IncidentDTO incidentDTO = new IncidentDTO();

            incidentDTO.toDTO(incident);
            incidentDTOList.add(incidentDTO);
        }
        return incidentDTOList;
    }


    @Override
    public IncidentDTO getIncidentById(Long id) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentException("Incident Not found", HttpStatus.NOT_FOUND));

        return incident.toDto(incident);
    }


    @Override
    public List<IncidentDTO> getLast20Incidents() {

        List<Incident> incidentList = incidentRepository.findLast20Incidents();
        List<IncidentDTO> incidentDTOList = new ArrayList<>();

        for (Incident incident : incidentList) {
            IncidentDTO incidentDTO = new IncidentDTO();

            incidentDTO.toDTO(incident);
            incidentDTOList.add(incidentDTO);
        }
        return incidentDTOList;
    }

}
