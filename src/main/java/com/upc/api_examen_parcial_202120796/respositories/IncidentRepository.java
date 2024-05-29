package com.upc.api_examen_parcial_202120796.respositories;

import com.upc.api_examen_parcial_202120796.dtos.IncidentXDateDTO;
import com.upc.api_examen_parcial_202120796.entities.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    @Query("select new com.upc.api_examen_parcial_202120796.dtos.IncidentXDateDTO(d.gcId ,count(i.gcId) , i.gcDateOcurrence) from Incident i join i.gcdepartament d where i.gcDateOcurrence = :gclocalDate group by d.gcId, i.gcDateOcurrence")
    List<IncidentXDateDTO> ListIndicentesForDate(LocalDate gclocalDate);
}
