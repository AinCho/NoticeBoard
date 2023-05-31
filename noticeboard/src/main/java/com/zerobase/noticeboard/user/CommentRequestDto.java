package com.zerobase.noticeboard.user;

import com.zerobase.noticeboard.domain.model.BaseEntity;
import com.zerobase.noticeboard.domain.model.Comment;
import com.zerobase.noticeboard.domain.model.Post;
import com.zerobase.user.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto extends BaseEntity {
    private Long id;
    private String comment;
    private Post post;
    private Member member;
    private String username;

    public static CommentRequestDto from(Comment comment) {
        return CommentRequestDto.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .post(comment.getPost())
                .member(comment.getMember())
                .build();
    }
}
