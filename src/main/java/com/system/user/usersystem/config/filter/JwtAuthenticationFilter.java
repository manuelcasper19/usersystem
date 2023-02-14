package com.system.user.usersystem.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.user.usersystem.config.AuthCredential;
import com.system.user.usersystem.config.TokenUtil;
import com.system.user.usersystem.config.impl.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //Metodo para crear token en el request
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        AuthCredential authCredential = new AuthCredential();
        try {
            authCredential = new ObjectMapper()
                    .readValue( request.getReader(), authCredential.getClass() );

        }catch( IOException e ){
           // e.printStackTrace();
        }
        UsernamePasswordAuthenticationToken userNameToken = new UsernamePasswordAuthenticationToken(
                authCredential.getEmail(),
                authCredential.getPassword(),
                Collections.emptyList());

        System.out.println("jWTAuthen " + userNameToken);
        return getAuthenticationManager().authenticate( userNameToken );
    }

    //Metodo para agregar el token a la response
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtil.createToken( userDetails.getUsername(), userDetails.getPassword() );
        System.out.println("JwtAuthentication metodo success " +token);
        response.addHeader("Authorization", "Bearer " + token );
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
