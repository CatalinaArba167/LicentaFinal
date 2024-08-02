package com.licenta.backendlicenta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CreateSellingPostDto {

    private UUID id;

    private UUID ownerId;

    private String ownerFirstName;

    private String ownerLastName;

    private String ownerEmail;

    private CarDto car;

    private String available;

    private String title;

    private String description;

    private LocalDateTime postDate;

    private List<MultipartFile> sellingPictures;
}
