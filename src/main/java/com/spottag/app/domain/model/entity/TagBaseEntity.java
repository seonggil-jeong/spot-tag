package com.spottag.app.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tag_base", schema = "public", indexes = {

})
public class TagBaseEntity {
    /**
     * Seq
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", updatable = false, nullable = false)
    private Long tagId;

    /**
     * 내용
     */
    @Column(name = "tag_content", nullable = false, length = 400)
    private String tagContent;


    /**
     * 위도
     */
    @Column(name = "latitude", nullable = false)
    private String latitude;

    /**
     * 경도
     */
    @Column(name = "longitude", nullable = false)
    private String longitude;

    /**
     * 생성자
     */
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    /**
     * 수정자
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 삭제자
     */
    @Column(name = "deleted_by")
    private String deletedBy;

    /**
     * 생성일
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * 수정일
     */
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    /**
     * 삭제일
     */
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "tagId", fetch = FetchType.LAZY)
    private List<LikeEntity> likeEntityList;


    @OneToOne(mappedBy = "tagId")
    @JoinColumn(name = "tag_image_id")
    private TagImageEntity tagImage;

    @OneToOne(mappedBy = "tagId")
    @JoinColumn(name = "tag_music_id")
    private TagMusicEntity tagMusic;


    @Builder
    public TagBaseEntity(
            String tagContent,
            String latitude,
            String longitude,
            String createdBy,
            TagImageEntity tagImageEntity,
            TagMusicEntity tagMusicEntity
    ) {
        this.tagContent = tagContent;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.tagImage = tagImageEntity;
        this.tagMusic = tagMusicEntity;

//        TODO ADD UserEntity
    }


    //    TODO FK by AccountEntity
}
