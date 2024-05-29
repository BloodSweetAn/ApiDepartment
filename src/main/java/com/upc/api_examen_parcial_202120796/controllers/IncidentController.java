package com.upc.api_examen_parcial_202120796.controllers;

import com.upc.api_examen_parcial_202120796.dtos.DepartamentDTO;
import com.upc.api_examen_parcial_202120796.dtos.IncidentDTO;
import com.upc.api_examen_parcial_202120796.entities.Department;
import com.upc.api_examen_parcial_202120796.entities.Incident;
import com.upc.api_examen_parcial_202120796.services.IncidentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/GuevaraCueva/api")
public class IncidentController {

    @Autowired
    private IncidentService gcIncidentService;

    @PostMapping("/incidentes")
    //user : Cristina Sosa
    //pass : cristina12
    public ResponseEntity<IncidentDTO> save (@RequestBody IncidentDTO gcindicentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Incident gcIncident = modelMapper.map(gcindicentDTO, Incident.class);
        try {
            gcIncidentService.save(gcIncident);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido guardar el incidente");
        }
        gcindicentDTO = modelMapper.map(gcIncident, IncidentDTO.class);
        return new ResponseEntity<>(gcindicentDTO, HttpStatus.OK);
    }

    @GetMapping("/quantities/{gcDate}")
    public ResponseEntity<?> ListIndicentesForDate(@PathVariable(name = "gcDate") LocalDate gcDate){
        try {
            return ResponseEntity.ok(gcIncidentService.ListIndicentesForDate(gcDate));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
