package com.upc.api_examen_parcial_202120796.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IncidentXDateDTO {
    private Long gcId;
    private Long gcIncidenteCount;
    private LocalDate gcDate;
}
