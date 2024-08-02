package com.licenta.backendlicenta.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
