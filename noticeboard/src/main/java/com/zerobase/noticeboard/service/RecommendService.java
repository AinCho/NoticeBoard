package com.zerobase.noticeboard.service;

import com.zerobase.noticeboard.domain.model.Post;
import com.zerobase.noticeboard.domain.model.Recommend;
import com.zerobase.noticeboard.domain.repository.PostRepository;
import com.zerobase.noticeboard.domain.repository.RecommendRepository;
import com.zerobase.noticeboard.exception.ErrorCode_b;
import com.zerobase.noticeboard.exception.PostException;
import com.zerobase.noticeboard.user.RecommendRequestDTO;
import com.zerobase.user.domain.entity.Member;
import com.zerobase.user.domain.repository.MemberRepository;
import com.zerobase.user.exception.ErrorCode;
import com.zerobase.user.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecommendService {
    private final RecommendRepository recommendRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public void insert(RecommendRequestDTO recommendRequestDTO) throws Exception{
        Member member = memberRepository.findById(recommendRequestDTO.getMemberId())
                .orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_USER));

        Post post = postRepository.findById(recommendRequestDTO.getPostId())
                .orElseThrow(() -> new PostException(ErrorCode_b.NOT_FOUND_POST));

        Recommend recommend = Recommend.builder()
                .post(post)
                .member(member)
                .build();

        recommendRepository.save(recommend);
    }

    @Transactional
    public void delete(RecommendRequestDTO recommendRequestDTO) {
        Member member = memberRepository.findById(recommendRequestDTO.getMemberId())
                .orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_USER));

        Post post = postRepository.findById(recommendRequestDTO.getPostId())
                .orElseThrow(() -> new PostException(ErrorCode_b.NOT_FOUND_POST));
    }
}
