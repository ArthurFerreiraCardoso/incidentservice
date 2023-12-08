package com.incidentsservice.diazero.repository;

import com.incidentsservice.diazero.model.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    boolean existsByName(String name);

    @Query(value = "SELECT * FROM incident ORDER BY createdAt DESC LIMIT 20", nativeQuery = true)
    List<Incident> findLast20Incidents();
}
