package com.upc.api_examen_parcial_202120796.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
/*
Saber el rol que nos identifica es muy importante ya que nos permite saber que funciones estamos permitidos hacer, en este caso hemos creado
dos roles ,  COMISARIO y SUBALTERNO, estos tienen la tarea tanto de registrar departamento e incidentes respectivamente. Esto nos permite
que al verificar que tanto el comisario no accesa a las funciones de subalterno teniendo encuenta que en la funcion tenga el preauthorizado
para que se haga correctamente la verificacion del rol.

Todo esto ayuda a que en nuestro programa cuente con la seguridad correspondiente.

 */
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gcId;
    @Column(nullable = false)
    private String gcRole;
}
