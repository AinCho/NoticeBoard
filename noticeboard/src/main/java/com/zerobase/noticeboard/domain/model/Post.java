package com.zerobase.noticeboard.domain.model;

import com.zerobase.noticeboard.domain.post.AddPostForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    private static Post of(AddPostForm form) {
        return Post.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .build();
    }
}
