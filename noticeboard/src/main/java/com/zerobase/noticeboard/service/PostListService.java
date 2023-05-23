package com.zerobase.noticeboard.service;

import com.zerobase.noticeboard.domain.model.Post;
import com.zerobase.noticeboard.domain.post.UpdatePostForm;
import com.zerobase.noticeboard.domain.repository.PostRepository;
import com.zerobase.noticeboard.exception.ErrorCode;
import com.zerobase.noticeboard.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostListService {
    private final PostRepository postRepository;

    @Transactional
    public Post updatePost(Long id, UpdatePostForm form) {
        Post post = postRepository.findById(form.getId())
                .filter(pi -> pi.getId().equals(id))
                .orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_POST));
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        return post;
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .filter(pi -> pi.getId().equals(id))
                .orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_POST));
        postRepository.delete(post);
    }
}
