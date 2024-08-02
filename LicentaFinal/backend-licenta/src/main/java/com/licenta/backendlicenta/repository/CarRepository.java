package com.licenta.backendlicenta.repository;

import com.licenta.backendlicenta.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CarRepository extends JpaRepository<Car, UUID> {

}
