package com.spottag.app.domain.model;

import com.spottag.app.domain.model.enums.AccountRoleEnums;
import com.spottag.app.domain.model.listener.AccountEntityListener;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AccountEntityListener.class)
@Entity
@Table(name = "account", indexes = {

})
public class AccountEntity {

    /**
     * Seq
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", updatable = false, nullable = false)
    private Long accountId;

    /**
     * 이메일
     */
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * 비밀번호
     */
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    /**
     * 닉네임
     */
    @Column(name = "nickname", nullable = false, length = 8)
    private String nickname;

    /**
     * 권한
     */
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private AccountRoleEnums role;

    /**
     * 삭제자
     */
    @Nullable
    @Column(name = "deleted_by")
    private Long deletedBy;

    /**
     * 생성일
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * 수정일
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 삭제일
     */
    @Setter
    @Nullable
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    public AccountEntity(
            String email,
            String password,
            String nickname,
            AccountRoleEnums role,
            Long deletedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.deletedBy = deletedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
