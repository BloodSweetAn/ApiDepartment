package com.upc.api_examen_parcial_202120796.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String gcsecret;

    public String getUsernameFromToken(String gctoken) {
        return getClaimFromToken(gctoken, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String gctoken) {
        return getClaimFromToken(gctoken, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String gctoken, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(gctoken);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String gctoken) { //El Body
        return Jwts.parser().setSigningKey(gcsecret).parseClaimsJws(gctoken).getBody();
    }

    private Boolean isTokenExpired(String gctoken) {
        final Date expiration = getExpirationDateFromToken(gctoken);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails gcuserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nombre", "rosa");
        return doGenerateToken(claims, gcuserDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, gcsecret).compact();//genera con la semilla y scret preconfig
    }

    public Boolean validateToken(String token, UserDetails gcuserDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(gcuserDetails.getUsername()) && !isTokenExpired(token));
    }
}