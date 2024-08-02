package com.licenta.backendlicenta.service;

import com.licenta.backendlicenta.domain.ProfilePicture;
import com.licenta.backendlicenta.domain.SellingPicture;
import com.licenta.backendlicenta.domain.SellingPost;
import com.licenta.backendlicenta.repository.SellingPictureRepository;
import com.licenta.backendlicenta.repository.SellingPostRepository;
import com.licenta.backendlicenta.utils.ImageUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellingPictureService {
    private final SellingPictureRepository sellingPictureRepository;

    public void uploadSellingPictures(SellingPost sellingPost, List<MultipartFile> files) throws IOException {
        for (MultipartFile file:files
             ) {
            SellingPicture sellingPicture = SellingPicture.builder()
                    .id(UUID.randomUUID())
                    .sellingPost(sellingPost)
                    .picture(ImageUtils.compressImage(file.getBytes()))
                    .build();
            sellingPictureRepository.saveSellingPicture(sellingPicture.getId(),sellingPicture.getPicture(),sellingPicture.getSellingPost().getId());
        }
    }

    public void updateSellingPictures(SellingPost sellingPost, List<MultipartFile> files) throws IOException {
        this.deleteSellingPicturesByPostId(sellingPost.getId());
        for (MultipartFile file:files
        ) {
            SellingPicture sellingPicture = SellingPicture.builder()
                    .id(UUID.randomUUID())
                    .sellingPost(sellingPost)
                    .picture(ImageUtils.compressImage(file.getBytes()))
                    .build();
            sellingPictureRepository.saveSellingPicture(sellingPicture.getId(),sellingPicture.getPicture(),sellingPicture.getSellingPost().getId());
        }
    }
    public List<byte[]> getSellingPicturesBySellingPostId(UUID sellingPostId) {
        Optional<List<byte[]>> sellingPicturesOptional = sellingPictureRepository.findBySellingPostId(sellingPostId);

        List<byte[]> sellingPictures = sellingPicturesOptional
                .orElseThrow(() -> new EntityNotFoundException("Selling picture error!"));

        return sellingPictures.stream()
                .map(ImageUtils::decompressImage)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSellingPicturesByPostId(UUID sellingPostId) {
        sellingPictureRepository.deleteBySellingPostId(sellingPostId);
    }


}
