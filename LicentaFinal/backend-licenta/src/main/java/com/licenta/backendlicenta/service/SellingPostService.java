package com.licenta.backendlicenta.service;

import com.licenta.backendlicenta.domain.SellingPost;
import com.licenta.backendlicenta.repository.SellingPostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SellingPostService {
    public static final String POST_NOT_FOUND = "Post not found!";
    private final SellingPostRepository sellingPostRepository;
    private final CarService carService;
    private final UserService userService;
    private final SellingPictureService sellingPictureService;

    public List<Map<SellingPost, List<byte[]>>> getLoggedInUserSellingPostsOrdered(UUID loggedInUserId) {
        userService.validateUserId(loggedInUserId);
        List<SellingPost> sellingPosts = sellingPostRepository.findByOwnerIdOrderByPostDate(loggedInUserId);
        return getSellingPostAndPictures(sellingPosts);
    }

    public List<Map<SellingPost, List<byte[]>>> getOtherUsersSellingPosts(UUID loggedInUserId) {
        userService.validateUserId(loggedInUserId);
        List<SellingPost> sellingPosts = sellingPostRepository.findDistinctByOwnerId(loggedInUserId);
        return getSellingPostAndPictures(sellingPosts);
    }

    private List<Map<SellingPost, List<byte[]>>> getSellingPostAndPictures(List<SellingPost> sellingPosts) {
        List<Map<SellingPost, List<byte[]>>> result = new ArrayList<>();

        for (SellingPost sellingPost : sellingPosts) {
            List<byte[]> pictures = sellingPictureService.getSellingPicturesBySellingPostId(sellingPost.getId());

            Map<SellingPost, List<byte[]>> map = new HashMap<>();
            map.put(sellingPost, pictures);

            result.add(map);
        }

        return result;
    }

    public Map<SellingPost,List<byte[]>> getSellingPostById(UUID sellingPostId){
        Map<SellingPost, List<byte[]>> result = new HashMap<>();
        Optional<SellingPost> sellingPost = sellingPostRepository.findById(sellingPostId);
        System.out.println(sellingPost.get());
        if(sellingPost.isPresent()){
            List<byte[]> pictures = sellingPictureService.getSellingPicturesBySellingPostId(sellingPostId);
            result.put(sellingPost.get(),pictures);
            return result;
        }
        else
            throw new EntityNotFoundException(POST_NOT_FOUND);
    }

    public SellingPost addSellingPost(SellingPost sellingPost) throws IOException {
        sellingPost.setCar(carService.addCar(sellingPost.getCar()));
        return sellingPostRepository.save(sellingPost);

    }

    public void deleteSellingPost(UUID postId) {
        SellingPost sellingPost = this.findByID(postId);
        sellingPictureService.deleteSellingPicturesByPostId(sellingPost.getId());
        sellingPostRepository.delete(sellingPost);
    }

    public SellingPost findByID(UUID postId) {
        Optional<SellingPost> sellingPost = sellingPostRepository.findById(postId);
        if (sellingPost.isPresent())
            return sellingPost.get();
        throw new EntityNotFoundException(POST_NOT_FOUND);
    }
}
