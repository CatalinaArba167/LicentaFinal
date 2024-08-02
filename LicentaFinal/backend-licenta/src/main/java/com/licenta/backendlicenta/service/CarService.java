package com.licenta.backendlicenta.service;

import com.licenta.backendlicenta.domain.Car;
import com.licenta.backendlicenta.domain.Location;
import com.licenta.backendlicenta.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final LocationService locationService;

    public Car addCar(Car newCar) {
        try{
            newCar.setLocation(locationService.findByCountryCountyAndCity(newCar.getLocation()));
        }catch (EntityNotFoundException e){
            newCar.setLocation(locationService.saveLocation(newCar.getLocation()));
        }
        return carRepository.save(newCar);
    }
}
