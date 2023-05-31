package com.zerobase.noticeboard.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendRequestDTO {
    private Long memberId;
    private Long postId;

    public RecommendRequestDTO(Long memberId, Long postId) {
        this.memberId = memberId;
        this.postId = postId;
    }
}
