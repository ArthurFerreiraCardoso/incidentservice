package com.incidentsservice.diazero.model.entity;

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


}

