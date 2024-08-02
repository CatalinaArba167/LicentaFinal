package com.licenta.backendlicenta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LocationDto {
    private UUID id;
    private String country;
    private String county;
    private String city;
}
