package com.licenta.backendlicenta.mapper;

import com.licenta.backendlicenta.domain.Car;
import com.licenta.backendlicenta.domain.Location;
import com.licenta.backendlicenta.domain.SellingPost;
import com.licenta.backendlicenta.domain.User;
import com.licenta.backendlicenta.dto.CarDto;
import com.licenta.backendlicenta.dto.CreateSellingPostDto;
import com.licenta.backendlicenta.dto.GetSellingPostDto;
import com.licenta.backendlicenta.dto.LocationDto;
import com.licenta.backendlicenta.enums.Availability;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GetSellingPostMapper {
    public List<GetSellingPostDto> cascadeToDto(List<Map<SellingPost, List<byte[]>>> sellingPostList) {
        return sellingPostList.stream()
                .map(entry -> {
                    SellingPost sellingPost = entry.keySet().iterator().next();
                    List<byte[]> pictures = entry.get(sellingPost);
                    List<String> base64Pictures = pictures.stream()
                            .map(picture -> Base64.getEncoder().encodeToString(picture))
                            .collect(Collectors.toList());
                    return toDto(sellingPost, base64Pictures);
                })
                .collect(Collectors.toList());
    }

    public GetSellingPostDto toDto(SellingPost sellingPost, List<String> sellingPictures) {
        return GetSellingPostDto.builder()
                .id(sellingPost.getId())
                .postDate(sellingPost.getPostDate())
                .available(String.valueOf(sellingPost.getAvailable()))
                .title(sellingPost.getTitle())
                .description(sellingPost.getDescription())
                .ownerId(sellingPost.getOwner().getId())
                .ownerFirstName(sellingPost.getOwner().getFirstName())
                .ownerLastName(sellingPost.getOwner().getLastName())
                .ownerEmail(sellingPost.getOwner().getEmail())
                .car(CarDto.builder()
                        .id(sellingPost.getCar().getId())
                        .location(LocationDto.builder()
                                .country(sellingPost.getCar().getLocation().getCountry())
                                .county(sellingPost.getCar().getLocation().getCounty())
                                .city(sellingPost.getCar().getLocation().getCity())
                                .build())
                        .price(sellingPost.getCar().getPrice())
                        .predictedPrice(sellingPost.getCar().getPredictedPrice())
                        .manufacturer(sellingPost.getCar().getManufacturer())
                        .model(sellingPost.getCar().getModel())
                        .prodYear(sellingPost.getCar().getProdYear())
                        .category(sellingPost.getCar().getCategory())
                        .leatherInterior(sellingPost.getCar().isLeatherInterior())
                        .fuelType(sellingPost.getCar().getFuelType())
                        .engineVolume(sellingPost.getCar().getEngineVolume())
                        .mileage(sellingPost.getCar().getMileage())
                        .cylinders(sellingPost.getCar().getCylinders())
                        .gearBoxType(sellingPost.getCar().getGearBoxType())
                        .driveWheels(sellingPost.getCar().getDriveWheels())
                        .doors(sellingPost.getCar().getDoors())
                        .wheel(sellingPost.getCar().getWheel())
                        .color(sellingPost.getCar().getColor())
                        .airbags(sellingPost.getCar().getAirbags())
                        .isTurbo(sellingPost.getCar().isTurbo())
                        .build())

                .sellingPictures(sellingPictures)
                .build();
    }

    public SellingPost toEntity(GetSellingPostDto sellingPostDto) {
        return SellingPost.builder()
                .id(sellingPostDto.getId())
                .postDate(sellingPostDto.getPostDate())
                .available(Availability.valueOf((sellingPostDto.getAvailable())))
                .title(sellingPostDto.getTitle())
                .description(sellingPostDto.getDescription())
                .owner(User.builder().id(sellingPostDto.getOwnerId()).build())
                .car(Car.builder()
                        .location(Location.builder()
                                .country(sellingPostDto.getCar().getLocation().getCountry())
                                .county(sellingPostDto.getCar().getLocation().getCounty())
                                .city(sellingPostDto.getCar().getLocation().getCity())
                                .build())
                        .price(sellingPostDto.getCar().getPrice())
                        .predictedPrice(sellingPostDto.getCar().getPredictedPrice())
                        .manufacturer(sellingPostDto.getCar().getManufacturer())
                        .model(sellingPostDto.getCar().getModel())
                        .prodYear(sellingPostDto.getCar().getProdYear())
                        .category(sellingPostDto.getCar().getCategory())
                        .leatherInterior(sellingPostDto.getCar().isLeatherInterior())
                        .fuelType(sellingPostDto.getCar().getFuelType())
                        .engineVolume(sellingPostDto.getCar().getEngineVolume())
                        .mileage(sellingPostDto.getCar().getMileage())
                        .cylinders(sellingPostDto.getCar().getCylinders())
                        .gearBoxType(sellingPostDto.getCar().getGearBoxType())
                        .driveWheels(sellingPostDto.getCar().getDriveWheels())
                        .doors(sellingPostDto.getCar().getDoors())
                        .wheel(sellingPostDto.getCar().getWheel())
                        .color(sellingPostDto.getCar().getColor())
                        .airbags(sellingPostDto.getCar().getAirbags())
                        .isTurbo(sellingPostDto.getCar().isTurbo())
                        .build())
                .build();
    }
}
