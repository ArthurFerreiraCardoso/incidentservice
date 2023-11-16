package com.incidentsservice.diazero.service;

import com.incidentsservice.diazero.exception.IncidentException;
import com.incidentsservice.diazero.model.IncidentMapper;
import com.incidentsservice.diazero.model.dto.IncidentDTO;
import com.incidentsservice.diazero.model.dto.IncidentUpdateRequest;
import com.incidentsservice.diazero.model.entity.Incident;
import com.incidentsservice.diazero.repository.IncidentReposity;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
public class IncidentServiceImpl implements IncidentService {

    private final IncidentMapper incidentMapper;
    private final IncidentReposity incidentReposity;

    public IncidentServiceImpl(IncidentMapper incidentMapper,
                               IncidentReposity incidentReposity) {
        this.incidentMapper = incidentMapper;
        this.incidentReposity = incidentReposity;
    }


    /**
     * Method to create and register an Incident
     *
     * @param incidentDTO
     * @return incident
     */
    @Override
    public IncidentDTO registerIncident(IncidentDTO incidentDTO) {

        log.info("Validate incident");
        validateIncident(incidentDTO);

        //converting
        Incident incident = incidentMapper.toEntity(incidentDTO);

        //save in repo
        incidentReposity.save(incident);

        return incidentMapper.toDto(incident);
    }

    /**
     * Method to update an incident
     *
     * @param id
     * @param updateRequest
     * @return
     */
    @Override
    public IncidentDTO updateIncident(Long id, IncidentUpdateRequest updateRequest) {

        //Find the incident by ID
        Incident incident = incidentReposity.findById(id)
                .orElseThrow(() -> new IncidentException("Incident not found", HttpStatus.NOT_FOUND));

        // Update the properties

        incident.setName(updateRequest.getName());
        incident.setDescription(updateRequest.getDescription());

        //save the updated incident to the database
        incidentReposity.save(incident);

        return incidentMapper.toDto(incident);
    }

    /**
     * Method to validate an Incident
     *
     * @param incident
     * @return an incidentDTO
     */
    @Override
    public IncidentDTO validateIncident(IncidentDTO incident) {

        if (incidentReposity.existsByName(StringUtils.toRootLowerCase(incident.getName()))
                || Objects.isNull(incident)) {
            throw new IncidentException("Not allowed to create incident", HttpStatus.BAD_REQUEST);
        }
        return incident;
    }


    /**
     * Method to delete incidents
     *
     * @param id
     */
    @Override
    public void deleteIncident(Long id) {
        //verify if exists

        Incident incident = incidentReposity.findById(id)
                .orElseThrow(() -> new IncidentException("Incident Not found", HttpStatus.NOT_FOUND));

        //delete the incident from the database
        incidentReposity.delete(incident);
    }

    /**
     * Method to retrieve all Incidents
     *
     * @return a list
     */
    @Override
    public List<IncidentDTO> getAllIncidents() {
        List<Incident> list = incidentReposity.findAll();

        return list.stream()
                .map(incidentMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Method to retrieve in incident by id
     *
     * @param id
     * @return the incident
     */
    @Override
    public IncidentDTO getIncidentById(Long id) {
        Incident incident = incidentReposity.findById(id)
                .orElseThrow(() -> new IncidentException("Incident Not found", HttpStatus.NOT_FOUND));

        return incidentMapper.toDto(incident);
    }

    /**
     * Method to retrieve a list of the last 20 incidents
     *
     * @return a list
     */
    @Override
    public List<IncidentDTO> getLast20Incidents() {
        List<Incident> incidentList = incidentReposity.findLast20Incidents();

        // Convert the list of entities to a list of DTOs
        return incidentList.stream()
                .map(incidentMapper::toDto)
                .collect(Collectors.toList());
    }


}
