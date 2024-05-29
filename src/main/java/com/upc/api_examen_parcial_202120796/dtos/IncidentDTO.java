package com.upc.api_examen_parcial_202120796.dtos;

import com.upc.api_examen_parcial_202120796.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IncidentDTO {
    private Long gcId;
    private String gcDetail;
    private LocalDate gcDateOcurrence;
    private Double gcLossCaused;
    private Department gcdepartament;
}
