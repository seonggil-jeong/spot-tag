package com.spottag.app.domain.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "like_info", schema = "public")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeId", updatable = false, nullable = false, unique = true)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false, updatable = false)
    private TagBaseEntity tagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private AccountEntity accountId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public LikeEntity(TagBaseEntity tagBaseEntity, AccountEntity accountEntity) {
        this.createdAt = LocalDateTime.now();
        this.tagId = tagBaseEntity;
        this.accountId = accountEntity;
    }
}
