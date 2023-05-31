package com.zerobase.noticeboard.domain.model;

import com.zerobase.user.domain.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @Column(columnDefinition = "TEXT", nullable = false, length = 1000)
    private String comment;
    @ColumnDefault("FALSE")
    @Column(nullable = false)
    private boolean isDeleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Comment(String comment) {
        this.comment = comment;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void updatePost(Post post) {
        this.post = post;
    }
}
