package com.upc.api_examen_parcial_202120796.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

//JwtRequest no sirve para encapsular la información de autenticación (nombre de usuario y contraseña) para la autenticación de usuarios.

public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String gcUsername;
    private String gcPassword;
}