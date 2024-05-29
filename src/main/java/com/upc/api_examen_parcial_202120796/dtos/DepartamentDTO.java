package com.upc.api_examen_parcial_202120796.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DepartamentDTO {
    private Long gcId;
    private String gcDescription;
    private String gcAuthorities;
    private int gcNroOfficers;
    private boolean gcOperative;
}
