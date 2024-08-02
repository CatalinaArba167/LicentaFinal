package com.licenta.backendlicenta.security;


import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthTokenFilter extends OncePerRequestFilter {
    public static final String BEARER = "Bearer ";
    public static final int HEADER_BEGIN_INDEX = 7;
    @Autowired
    private JwtUtil jwtHelper;
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * This method is responsible for processing the incoming HTTP requests by
     * validating and processing JWT tokens. If a valid JWT token is found in the
     * request, it is used to authenticate the user.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwtToken = parseJwt(request);
            if (jwtToken != null && jwtHelper.validateJwtToken(jwtToken)) {
                String username = jwtHelper.extractEmail(jwtToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (ExpiredJwtException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (headerAuth != null && headerAuth.startsWith(BEARER)) {
            return headerAuth.substring(HEADER_BEGIN_INDEX);
        }
        return null;
    }

}
