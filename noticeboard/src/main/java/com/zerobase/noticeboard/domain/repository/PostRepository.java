package com.zerobase.noticeboard.domain.repository;

import com.zerobase.noticeboard.domain.model.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    @EntityGraph(attributePaths = {"postLists"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Post> findPostByCreatedAt(LocalDateTime createdAt);

    @EntityGraph(attributePaths = {"postLists"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Post> findPostByTitle(String title);

    @EntityGraph(attributePaths = {"postLists"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Post> findPostById(Long id);

    @EntityGraph(attributePaths = {"postLists"}, type = EntityGraph.EntityGraphType.LOAD)
    Post findPostByCommentCount(int commentCount);

    @EntityGraph(attributePaths = {"postLists"}, type = EntityGraph.EntityGraphType.LOAD)
    Post findPostByRecommendCount(int recommendCount);
}
