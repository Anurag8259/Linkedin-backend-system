package com.LinkedInProject.postsService.service;

import com.LinkedInProject.postsService.dto.PostCreateRequestDto;
import com.LinkedInProject.postsService.dto.PostDto;
import com.LinkedInProject.postsService.entity.Post;
import com.LinkedInProject.postsService.exception.ResourceNotFoundException;
import com.LinkedInProject.postsService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto,Long userId) {
        log.info("Creating post for user with id {},",userId);
        Post post=modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        post=postRepository.save(post);
        return modelMapper.map(post,PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        log.info("Getting the post with Id: {}",postId);
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found with Id: "+postId));
        return modelMapper.map(post,PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        log.info("Getting all the posts of a user with ID: {}", userId);
        List<Post> postList=postRepository.findByUserId(userId);

        return postList
                .stream()
                .map((element)->modelMapper.map(element,PostDto.class)).collect(Collectors.toList());
    }

}
