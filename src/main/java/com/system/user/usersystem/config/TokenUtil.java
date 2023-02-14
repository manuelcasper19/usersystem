package com.system.user.usersystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    //Clave secreta de token
    private final static String ACCESS_TOKEN_SECRET = "StiveNLINaisables242322odldld4545423232";
    //Tiempo de expiracicion
    private final static Long ACCESS_TOKEN_EXPIRE = 600000L;

    //Metodo de creación de token
    public static String createToken( String fullName, String email ){
        Date expirationToken = new Date( System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE );
        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", fullName);

        System.out.println( expirationToken );
        return Jwts.builder()
                .setSubject(email)
                .setExpiration( expirationToken )
                .addClaims( extra )
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET.getBytes())
                .compact();
    }

    //Metodo Validación token
    public static UsernamePasswordAuthenticationToken getAuthenticationToken( String token ){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey( ACCESS_TOKEN_SECRET.getBytes() )
                    .build()
                    .parseClaimsJws( token )
                    .getBody();
            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList() );


        }catch ( JwtException j ){
           return null;
        }
    }

}
