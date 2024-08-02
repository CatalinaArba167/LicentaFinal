package com.licenta.backendlicenta.controller;

import com.licenta.backendlicenta.service.SellingPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/selling_picture")
@RestController
@RequiredArgsConstructor
public class SellingPictureController {
    private final SellingPictureService sellingPictureService;

//    @PostMapping("/{id}")
//    public void uploadSellingPicture(@PathVariable UUID id, @RequestParam("sellingPictures") List<MultipartFile> sellingPictures) {
//        try {
//            sellingPictureService.uploadSellingPictures(id, sellingPictures);
//
//        } catch (IOException e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload selling picture.");
//        } catch (RuntimeException e) {
//            e.printStackTrace(); // Log the exception or handle it as necessary
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found.");
//        }
//
//    }
}
