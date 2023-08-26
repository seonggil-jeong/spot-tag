package com.spottag.app.domain.model.entity;

import com.spottag.app.domain.model.entity.LikeEntity;
import com.spottag.app.domain.model.listener.AccountEntityListener;
import com.spottag.enums.AccountRoleEnums;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;


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
    @Id
    @Column(name = "account_id", updatable = false, nullable = false, unique = true)
    private String accountId;

    /**
     * 이메일
     */
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * 비밀번호
     */
    @Column(name = "password", nullable = false, length = 400)
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


    @OneToMany(mappedBy = "accountId", fetch = FetchType.LAZY)
    private List<LikeEntity> likeEntityList;

    @OneToMany(mappedBy = "accountId", fetch = FetchType.LAZY)
    private List<TagBaseEntity> tagBaseEntityList;

    @Builder
    public AccountEntity(
            String accountId,
            String email,
            String password,
            String nickname,
            AccountRoleEnums role,
            Long deletedBy,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.deletedBy = deletedBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

}
