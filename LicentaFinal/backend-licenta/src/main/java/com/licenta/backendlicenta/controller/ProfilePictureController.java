package com.licenta.backendlicenta.controller;

import com.licenta.backendlicenta.service.ProfilePictureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@RequestMapping("/profile_picture")
@RestController
@RequiredArgsConstructor
public class ProfilePictureController {
    private final ProfilePictureService profilePictureService;

    @PostMapping("/{id}")
    public void uploadProfilePicture(@PathVariable UUID id, @RequestParam("profilePicture") MultipartFile profilePicture) {
        try {
            profilePictureService.uploadProfilePicture(id, profilePicture);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload profile picture.");
        } catch (RuntimeException e) {
            e.printStackTrace(); // Log the exception or handle it as necessary
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found.");
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable UUID id) {
        try {
            byte[] imageData = profilePictureService.getProfilePicture(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(imageData);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
