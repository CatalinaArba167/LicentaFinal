package com.licenta.backendlicenta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CarDto {
    private UUID id;
    private LocationDto location;
    private int price;
    private int predictedPrice;
    private String manufacturer;
    private String model;
    private int prodYear;
    private String category;
    private boolean leatherInterior;
    private String fuelType;
    private double engineVolume;
    private int mileage;
    private int cylinders;
    private String gearBoxType;
    private String driveWheels;
    private String doors;
    private String wheel;
    private String color;
    private int airbags;

    @JsonProperty("isTurbo")
    private boolean isTurbo;
}
