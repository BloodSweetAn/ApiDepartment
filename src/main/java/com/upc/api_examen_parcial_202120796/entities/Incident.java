package com.upc.api_examen_parcial_202120796.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gcId;
    @Column(nullable = false)
    private String gcDetail;
    @Column(nullable = false)
    private LocalDate gcDateOcurrence;
    @Column(nullable = false)
    private Double gcLossCaused;
    @ManyToOne
    @JoinColumn(name = "djgcdepartament_gcId")
    private Department gcdepartament;
}
