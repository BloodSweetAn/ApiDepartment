package com.upc.api_examen_parcial_202120796.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String gcjwttoken;

    public String getGcjwttoken() {
        return gcjwttoken;
    }

    public JwtResponse(String jwttoken) {
        super();
        this.gcjwttoken = jwttoken;
    }
}