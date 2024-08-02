package com.licenta.backendlicenta.controller;

import com.licenta.backendlicenta.dto.CredentialsDto;
import com.licenta.backendlicenta.dto.LoginResult;
import com.licenta.backendlicenta.service.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResult> login(@RequestBody @NonNull CredentialsDto credentialsDto) {
        return new ResponseEntity<>(authService.login(credentialsDto.getEmail(), credentialsDto.getPassword()), HttpStatus.OK);
    }

}
