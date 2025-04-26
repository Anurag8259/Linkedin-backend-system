package com.LinkedInProject.postsService.controller;

import com.LinkedInProject.postsService.service.PostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/likes")
@RestController
public class PostLikesController {

    private final PostLikeService postLikeService;

    public PostLikesController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId){
        postLikeService.likePost(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId){
        postLikeService.unlikePost(postId);
        return ResponseEntity.noContent().build();
    }
}
