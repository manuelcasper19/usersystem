package com.system.user.usersystem.config.filter;


import com.system.user.usersystem.config.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    //Metodo para filtrar token que viene en la petición
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        System.out.println("Barrer token do filter" + bearerToken);
        if( bearerToken != null && bearerToken.startsWith("Bearer ")){
            String token = bearerToken.replace("Bearer ", "");
            System.out.println("Token jwtAuthorization do filte " + token);
            UsernamePasswordAuthenticationToken userNameToken = TokenUtil.getAuthenticationToken( token );
            SecurityContextHolder.getContext().setAuthentication( userNameToken );
        }else {
            System.out.println("Token no valido ");
        }
        filterChain.doFilter( request, response );

    }
}
