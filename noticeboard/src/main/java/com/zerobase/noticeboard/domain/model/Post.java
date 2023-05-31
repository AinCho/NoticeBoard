package com.zerobase.noticeboard.domain.model;

import com.zerobase.noticeboard.domain.post.AddPostForm;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;
    @ColumnDefault("0")
    @Column(name = "view_count", nullable = false)
    private Integer viewCount;

    private static Post of(AddPostForm form) {
        return Post.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
