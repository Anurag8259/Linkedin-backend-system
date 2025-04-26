package com.LinkedInProject.postsService.repository;

import com.LinkedInProject.postsService.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    boolean existsByUserIdAndPostId(Long userId, Long postId);
}
