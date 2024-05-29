package com.upc.api_examen_parcial_202120796.services;

import com.upc.api_examen_parcial_202120796.dtos.IncidentXDateDTO;
import com.upc.api_examen_parcial_202120796.entities.Incident;
import com.upc.api_examen_parcial_202120796.respositories.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncidentService {
    @Autowired
    private IncidentRepository gcincidentRepository;

    public Incident save(Incident gcIncident) {
        return gcincidentRepository.save(gcIncident);
    }
    public List<Incident> list() {
        return gcincidentRepository.findAll();
    }
    public List<IncidentXDateDTO> ListIndicentesForDate(LocalDate gcDate){
        return gcincidentRepository.ListIndicentesForDate(gcDate);
    }
}
