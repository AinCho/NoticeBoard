package com.zerobase.noticeboard.user;

import com.zerobase.noticeboard.domain.model.Comment;
import com.zerobase.user.domain.entity.Member;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private Long id;
    private String comment;
    private Member member;

    public static CommentResponseDto from(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .member(comment.getMember())
                .build();
    }
}
