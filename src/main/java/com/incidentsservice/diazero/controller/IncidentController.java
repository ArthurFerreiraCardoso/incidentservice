package com.incidentsservice.diazero.controller;

import com.incidentsservice.diazero.exception.IncidentException;
import com.incidentsservice.diazero.model.dto.IncidentDTO;
import com.incidentsservice.diazero.model.dto.IncidentUpdateRequest;
import com.incidentsservice.diazero.service.IncidentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/incidents")
@Tag(name = "Incidents", description = "Manage Incidents")
public class IncidentController {

    private final IncidentServiceImpl service;


    public IncidentController(IncidentServiceImpl service) {
        this.service = service;
    }


    @Operation(summary = "Register an incident ", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @PostMapping(name = "/register")
    public ResponseEntity<IncidentDTO> register(@RequestBody IncidentDTO incidentDTO) {

        try {
            IncidentDTO dto = service.registerIncident(incidentDTO);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (IncidentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update an incident ", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })

    @PutMapping("/update/{id}")
    public ResponseEntity<IncidentUpdateRequest> updateIncident(@PathVariable Long id,
                                                      @RequestBody IncidentUpdateRequest updateRequest) {
        // Call the service to update the incident
        IncidentUpdateRequest updatedIncident = service.updateIncident(id, updateRequest);

        // Return a ResponseEntity with the updated incident and a status code of 200 (OK)
        return new ResponseEntity<>(updatedIncident, HttpStatus.OK);

    }

    @Operation(summary = "Delete an incident ", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<IncidentDTO> deleteIncident(@PathVariable Long id) {
        // Call the service to delete the incident
        service.deleteIncident(id);

        // Return a ResponseEntity with a status code of 204 (No Content) for a successful delete
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Retrieve list of incidents ", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping("/list")

    public ResponseEntity<List<IncidentDTO>> listAllIncidents() {

        // Return a ResponseEntity with the list of incidents and a status code of 200 (OK)
        return new ResponseEntity<>(service.getAllIncidents(), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve incidents by id ", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<IncidentDTO> getIncidentById(@PathVariable Long id) {
        try {
            // call and return a ResponseEntity with the incident and a status code of 200 (OK) if exists
            return new ResponseEntity<>(service.getIncidentById(id), HttpStatus.OK);
        } catch (IncidentException e) {
            // If the incident is not found, return a ResponseEntity with a status code of 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = " Retrieve last 20 incidents", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No Content"),
    })
    @GetMapping("/listLast20")
    public ResponseEntity<List<IncidentDTO>> listLast20Incidents() {

        // call the service to retrieve a list of incidents and a status code of 200 (OK)
        return new ResponseEntity<>(service.getLast20Incidents(), HttpStatus.OK);
    }
}
