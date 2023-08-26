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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_image_id")
    private Long tagImageId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    @OneToOne
    @JoinColumn(name = "tag_id")
    private TagBaseEntity tagId;

//    TODO Add Columns

    @Builder
    public TagImageEntity(TagBaseEntity tagBaseEntity) {
        this.createdAt = LocalDateTime.now();
        this.tagId = tagBaseEntity;
    }
}
