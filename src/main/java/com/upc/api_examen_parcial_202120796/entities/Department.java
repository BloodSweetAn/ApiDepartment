package com.upc.api_examen_parcial_202120796.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gcId;
    @Column(nullable = false)
    private String gcDescription;
    @Column(nullable = false)
    private String gcAuthorities;
    @Column(nullable = false)
    private int gcNroOfficers;
    @Column(nullable = false)
    private boolean gcOperative;
}
