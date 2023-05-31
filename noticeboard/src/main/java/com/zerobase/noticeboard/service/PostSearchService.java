package com.zerobase.noticeboard.service;

import com.zerobase.noticeboard.domain.model.Post;
import com.zerobase.noticeboard.domain.repository.PostRepository;
import com.zerobase.noticeboard.exception.ErrorCode_b;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostSearchService {
    private final PostRepository postRepository;

    public List<Post> searchByTitle(String title) {
        return postRepository.searchByTitle(title);
    }

    public Post getByPostId(Long postId) {
        return postRepository.findPostById(postId)
                .orElseThrow(() -> new MemberException(ErrorCode_b.NOT_FOUND_ID));
    }

    public Post searchByCreatedDate(LocalDateTime createdAt) {
        return postRepository.findPostByCreatedAt(createdAt)
                .orElseThrow(() -> new MemberException(ErrorCode_b.NOT_FOUND_POSTBYCREATEDAT));
    }

    public Post searchByCommentCount(int commentCount) {
        return postRepository.findPostByCommentCount(commentCount);
    }

    public Post searchByRecommendCount(int recommendCount) {
        return postRepository.findPostByRecommendCount(recommendCount);
    }
}
