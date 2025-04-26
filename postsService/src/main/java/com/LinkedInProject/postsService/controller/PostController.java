package com.LinkedInProject.postsService.controller;

import com.LinkedInProject.postsService.dto.PostCreateRequestDto;
import com.LinkedInProject.postsService.dto.PostDto;
import com.LinkedInProject.postsService.entity.Post;
import com.LinkedInProject.postsService.repository.PostRepository;
import com.LinkedInProject.postsService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto,
                                              HttpServletRequest httpServletRequest){
        PostDto postDto=postService.createPost(postCreateRequestDto,1L);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        PostDto postDto=postService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/user/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostOfUser(@PathVariable Long userId){
        List<PostDto> posts=postService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(posts);
    }
}
