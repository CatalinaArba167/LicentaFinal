package com.licenta.backendlicenta.service;

import com.licenta.backendlicenta.domain.User;
import com.licenta.backendlicenta.dto.LoginResult;
import com.licenta.backendlicenta.security.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    public static final String USER_NOT_AUTHENTICATED = "User not authenticated";

    public final UserService userService;

    public final PasswordEncoder passwordEncoder;

    public final JwtUtil jwtHelper;

    /**
     * @param password The password of the user.
     * @return A LoginResult object
     * @throws ResponseStatusException If authentication fails (a user with the the username wasn't found or a mismatch
     *                                 between password and username), an UNAUTHORIZED status exception is thrown.
     * @throws EntityNotFoundException If the user with the provided username is not found.
     */
    public LoginResult login(String email, String password) {
        try {
            User user = userService.findByEmail(email);


            if (passwordEncoder.matches(password, user.getPassword())) {
                Map<String, String> claims = new HashMap<>();
                claims.put("email", user.getEmail());
                claims.put("userId", user.getId().toString());

                String jwt = jwtHelper.createJwtForClaims(user.getEmail(), claims);
                return new LoginResult(jwt);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_NOT_AUTHENTICATED);
            }
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_NOT_AUTHENTICATED);
        }
    }

}
