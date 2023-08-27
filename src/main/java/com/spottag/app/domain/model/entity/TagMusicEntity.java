package com.spottag.app.domain.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tag_music", schema = "public", indexes = {

})
@ToString
public class TagMusicEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "tag_music_id", nullable = false, updatable = false, unique = true)
    private Long tagMusicId;

    /**
     * 제목
     */
    @Column(name = "music_title", nullable = false)
    private String musicTitle;


    @Column(name = "artist_name", nullable = false)
    private String artistName;

    /**
     * track 식별 아이디
     */
    @Column(name = "track_id", nullable = false)
    private String trackId;

    /**
     * track url -> move spotify Page
     */
    @Column(name = "track_url", nullable = false)
    private String trackUrl;

    /**
     * 음원 미리듣기 URL
     */
    @Column(name = "preview_url")
    private String previewUrl;

    /**
     * 큰 사이지 이미지
     */
    @Column(name = "l_image_url")
    private String lImageUrl;

    /**
     * 중간 사이즈 이미지
     */
    @Column(name = "m_image_url")
    private String mImageUrl;

    /**
     * 작은 사이즈 이미지
     */
    @Column(name = "s_image_url")
    private String sImageUrl;

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
    public TagMusicEntity(String musicTitle,
                          String artistName,
                          String trackId, String trackUrl, String previewUrl,
                          String lImageUrl, String mImageUrl, String sImageUrl, TagBaseEntity tagId) {
        this.musicTitle = musicTitle;
        this.artistName = artistName;
        this.trackId = trackId;
        this.trackUrl = trackUrl;
        this.previewUrl = previewUrl;
        this.lImageUrl = lImageUrl;
        this.mImageUrl = mImageUrl;
        this.sImageUrl = sImageUrl;
        this.createdAt = LocalDateTime.now();
        this.tagId = tagId;
    }


    public TagMusicEntity updateTagMusic(
            final String musicTitle,
            final String artistName,
            final String trackId,
            final String trackUrl,
            final String previewUrl,
            final String lImageUrl,
            final String mImageUrl,
            final String sImageUrl
    ) {
        this.musicTitle = musicTitle;
        this.artistName = artistName;
        this.trackId = trackId;
        this.trackUrl = trackUrl;
        this.previewUrl = previewUrl;
        this.lImageUrl = lImageUrl;
        this.mImageUrl = mImageUrl;
        this.sImageUrl = sImageUrl;

        return this;
    }
}
