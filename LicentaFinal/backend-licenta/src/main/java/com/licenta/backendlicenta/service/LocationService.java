package com.licenta.backendlicenta.service;

import com.licenta.backendlicenta.domain.Location;
import com.licenta.backendlicenta.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    public static final String LOCATION_NOT_FOUND = "LOCATION NOT FOUND";

    private final LocationRepository locationRepository;

    public Location findByCountryCountyAndCity(Location location){
        Optional<Location> foundLocation=locationRepository.findByCountryAndCountyAndCity(location.getCountry(), location.getCounty(), location.getCity());
        if(foundLocation.isPresent())
            return foundLocation.get();
        else throw new EntityNotFoundException(LOCATION_NOT_FOUND);
    }

    public Location saveLocation(Location location){
            return locationRepository.save(location);
    }
}
