package com.licenta.backendlicenta.config;//package com.licenta.backendlicenta.config;

import com.licenta.backendlicenta.security.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private static final String CHAT_PATH = "/chat/**";
    private static final String SELLING_POST_PATH = "/selling_post/**";
    private static final String MESSAGE_PATH = "/messages/**";
    private static final String API_AUTH_LOGIN = "/auth/login";
    private static final String USERS_PATH = "/users";
    private static final String HEALTH_PATH = "/health";
    private static final String USERS_PATH2 = "/users/**";
    private static final String PROFILE_PICTURE = "profile_picture/**";
    private static final String SELLING_PICTURE = "selling_picture/**";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(SELLING_PICTURE,PROFILE_PICTURE,USERS_PATH2,SELLING_POST_PATH,MESSAGE_PATH, CHAT_PATH, USERS_PATH, API_AUTH_LOGIN).permitAll()
                        .requestMatchers(HttpMethod.GET, HEALTH_PATH).permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
}
