package com.spottag.app.domain.model;


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

    @ManyToOne
    @JoinColumn(name = "TagSeq")
    private TagBaseEntity tagId;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private AccountEntity accountId;

    @Column(name = "like_date")
    private LocalDateTime likeDate;

    @Builder
    public LikeEntity(LocalDateTime likeDate) {
        this.likeDate = likeDate;
    }
}
