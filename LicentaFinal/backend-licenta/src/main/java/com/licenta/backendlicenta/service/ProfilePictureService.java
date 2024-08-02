package com.licenta.backendlicenta.service;

import com.licenta.backendlicenta.domain.ProfilePicture;
import com.licenta.backendlicenta.domain.User;
import com.licenta.backendlicenta.repository.ProfilePictureRepository;
import com.licenta.backendlicenta.utils.ImageUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfilePictureService {
    private final ProfilePictureRepository profilePictureRepository;
    private final UserService userService;

    public void uploadProfilePicture(UUID userId, MultipartFile file) throws IOException {
        Optional<UUID> profilePictureID=profilePictureRepository.getProfilePictureIdByUserId(userId);
        if(profilePictureID.isPresent()){
            this.editProfilePicture(profilePictureID.get(),file,userId);
            return;
        }
        ProfilePicture profilePictureToBeSaved=new ProfilePicture();
        profilePictureToBeSaved.setId(UUID.randomUUID());
        profilePictureToBeSaved.setUser(userService.findByID(userId));
        profilePictureToBeSaved.setProfilePicture(ImageUtils.compressImage(file.getBytes()));
        profilePictureRepository.saveProfilePicture(profilePictureToBeSaved.getId(), profilePictureToBeSaved.getProfilePicture(), profilePictureToBeSaved.getUser().getId());

    }


    public void editProfilePicture(UUID id, MultipartFile file,UUID userId) throws IOException {
        profilePictureRepository.updateProfilePicture(id, ImageUtils.compressImage(file.getBytes()),userId);
    }

    public byte[] getProfilePicture(UUID userId) {
        userService.validateUserId(userId);
        Optional<byte[]> profilePicture = profilePictureRepository.getProfilePictureByUserId(userId);
        return profilePicture.map(ImageUtils::decompressImage)
                .orElseThrow(() -> new EntityNotFoundException("Profile picture not found!"));
    }

}
