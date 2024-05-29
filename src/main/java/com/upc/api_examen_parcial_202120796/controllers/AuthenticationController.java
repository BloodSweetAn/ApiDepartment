package com.upc.api_examen_parcial_202120796.controllers;

import com.upc.api_examen_parcial_202120796.security.JwtRequest;
import com.upc.api_examen_parcial_202120796.security.JwtResponse;
import com.upc.api_examen_parcial_202120796.security.JwtTokenUtil;
import com.upc.api_examen_parcial_202120796.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/GuevaraCueva")
/*
En el spring security tambien se ha tenido en cuenta la autentifucicacion del usuario en este caso debemos ser ya usuarios registrados
con sus propios roles para poder acceder al programa.

Este authemtication nos permite autentificarnos con un token para que sea mas seguro realizar cualquier accion que tengamos a nuestra
disposicion porque en este caso, esto nos ayuda a mejorar volviendola mas eficiente.

 */
public class AuthenticationController {
    @Autowired
    private AuthenticationManager gcauthenticationManager;
    @Autowired
    private JwtTokenUtil gcjwtTokenUtil;
    @Autowired
    private JwtUserDetailsService gcuserDetailsService;
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest gcauthenticationRequest) throws Exception {
        authenticate(gcauthenticationRequest.getGcUsername(), gcauthenticationRequest.getGcPassword());
        final UserDetails userDetails = gcuserDetailsService.loadUserByUsername(gcauthenticationRequest.getGcUsername());
        final String gctoken = gcjwtTokenUtil.generateToken(userDetails);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", gctoken);
        return ResponseEntity.ok(new JwtResponse(gctoken));
    }
    private void authenticate(String gcusername, String gcpassword) throws Exception {
        try {
            gcauthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(gcusername, gcpassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}