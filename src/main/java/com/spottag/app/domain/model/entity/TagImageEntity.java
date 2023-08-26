package com.spottag.app.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tag_image", schema = "public", indexes = {

})
public class TagImageEntity {

    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_image_id")
    private Long tagImageId;

    /**
     * 생성일
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    /**
     * Tag Base Id (FK)
     */
    @OneToOne
    @JoinColumn(name = "tag_id")
    private TagBaseEntity tagId;

    @Builder
    public TagImageEntity(TagBaseEntity tagBaseEntity) {
        this.createdAt = LocalDateTime.now();
        this.tagId = tagBaseEntity;
    }
}
