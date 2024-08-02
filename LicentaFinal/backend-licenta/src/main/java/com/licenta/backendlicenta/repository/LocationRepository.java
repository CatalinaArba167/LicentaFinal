package com.licenta.backendlicenta.repository;

import com.licenta.backendlicenta.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {

    Optional<Location> findByCountryAndCountyAndCity(String country, String county, String city);
}
