package com.licenta.backendlicenta.controller;

import com.licenta.backendlicenta.domain.SellingPost;
import com.licenta.backendlicenta.dto.CreateSellingPostDto;
import com.licenta.backendlicenta.dto.GetSellingPostDto;
import com.licenta.backendlicenta.mapper.GetSellingPostMapper;
import com.licenta.backendlicenta.mapper.SaveSellingPostMapper;
import com.licenta.backendlicenta.service.SellingPictureService;
import com.licenta.backendlicenta.service.SellingPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/selling_post")
public class SellingPostController {

    private final SellingPostService sellingPostService;
    private final SellingPictureService sellingPictureService;
    private final SaveSellingPostMapper sellingPostMapper;
    private final GetSellingPostMapper getSellingPostMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SellingPost> createSellingPost(@RequestPart("sellingPost") CreateSellingPostDto sellingPostDto,
                                                         @RequestPart("sellingPictures") List<MultipartFile> sellingPictures) {
        try {
            SellingPost sellingPost = sellingPostService.addSellingPost(sellingPostMapper.toEntity(sellingPostDto));
            sellingPictureService.uploadSellingPictures(sellingPost, sellingPictures);
            return new ResponseEntity<>(sellingPost, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SellingPost> updateSellingPost(@RequestPart("sellingPost") GetSellingPostDto sellingPostDto,
                                                         @RequestPart("sellingPictures") List<MultipartFile> sellingPictures) {

        try {
            SellingPost sellingPost = getSellingPostMapper.toEntity(sellingPostDto);

            SellingPost updatedSellingPost = sellingPostService.addSellingPost(sellingPost);

            if (sellingPictures != null && !sellingPictures.isEmpty()) {
                sellingPictureService.updateSellingPictures(updatedSellingPost, sellingPictures);
            }

            return new ResponseEntity<>(updatedSellingPost, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get_by_id/{sellingPostId}")
    public ResponseEntity<GetSellingPostDto> getSellingPostById(@PathVariable UUID sellingPostId) {
        Map<SellingPost, List<byte[]>> sellingPost = sellingPostService.getSellingPostById(sellingPostId);
        Map.Entry<SellingPost, List<byte[]>> entry = sellingPost.entrySet().iterator().next();
        GetSellingPostDto sellingPostDto = getSellingPostMapper.toDto(entry.getKey(), Collections.singletonList(entry.getValue().toString()));
        return new ResponseEntity<>(sellingPostDto, HttpStatus.OK);
    }

    @GetMapping("/get_all/{userId}")
    public ResponseEntity<List<GetSellingPostDto>> getAllPosts(@PathVariable UUID userId) {
        List<Map<SellingPost, List<byte[]>>> sellingPosts = sellingPostService.getOtherUsersSellingPosts(userId);
        List<GetSellingPostDto> sellingPostDtos = getSellingPostMapper.cascadeToDto(sellingPosts);
        return new ResponseEntity<>(sellingPostDtos, HttpStatus.OK);
    }

    @GetMapping("/get_my/{userId}")
    public ResponseEntity<List<GetSellingPostDto>> getMyPosts(@PathVariable UUID userId) {
        List<Map<SellingPost, List<byte[]>>> sellingPosts = sellingPostService.getLoggedInUserSellingPostsOrdered(userId);
        List<GetSellingPostDto> sellingPostDtos = getSellingPostMapper.cascadeToDto(sellingPosts);
        return new ResponseEntity<>(sellingPostDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable UUID postId) {
        sellingPostService.deleteSellingPost(postId);
    }


}
