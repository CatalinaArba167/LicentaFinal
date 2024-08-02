package com.licenta.backendlicenta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@SuperBuilder
public class Car extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    private int price;

    @Column(name = "predicted_price")
    private int predictedPrice;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "prod_year")
    private Integer prodYear;

    @Column(name = "category")
    private String category;

    @Column(name = "leather_interior")
    private boolean leatherInterior;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "engine_volume")
    private double engineVolume;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "cylinders")
    private int cylinders;

    @Column(name = "gear_box_type")
    private String gearBoxType;

    @Column(name = "drive_wheels")
    private String driveWheels;

    @Column(name = "doors")
    private String doors;

    @Column(name = "wheel")
    private String wheel;

    @Column(name = "color")
    private String color;

    @Column(name = "airbags")
    private int airbags;

    @Column(name = "is_turbo")
    private boolean isTurbo;

}
