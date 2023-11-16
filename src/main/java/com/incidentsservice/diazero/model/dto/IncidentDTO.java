package com.incidentsservice.diazero.model.dto;

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

}
