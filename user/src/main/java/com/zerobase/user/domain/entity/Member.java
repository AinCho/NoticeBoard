package com.zerobase.user.domain.entity;

import com.zerobase.user.domain.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Locale;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Member extends BaseEntity{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String username;
    private String password;

    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;

    @Column(columnDefinition = "int default 0")
    private Integer balance;

    public static Member from(SignUpForm form) {
        return Member.builder()
                .id(form.getId())
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .username(form.getUsername())
                .password(form.getPassword())
                .verify(false)
                .build();
    }
}
