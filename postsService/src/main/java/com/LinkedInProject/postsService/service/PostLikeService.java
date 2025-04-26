package com.LinkedInProject.postsService.service;

import com.LinkedInProject.postsService.entity.Post;
import com.LinkedInProject.postsService.exception.ResourceNotFoundException;
import com.LinkedInProject.postsService.repository.PostLikeRepository;
import com.LinkedInProject.postsService.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostLikeService(PostLikeRepository postLikeRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.postLikeRepository = postLikeRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public void likePost(Long postId) {
        Long userId=1L;
        log.info("User with ID :{} liking the post with ID :{}",userId,postId);

        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found with Id: "+postId));
        boolean hasAlreadyLinked=postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(hasAlreadyLinked) throw new BadRequestException("You cannot like the post again");
    }

    public void unlikePost(Long postId) {
    }
}
