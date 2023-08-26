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

    /**
     * 설명
     */
    @Column(name = "music_description", length = 100)
    private String musicDescription;

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
    public TagMusicEntity(String musicTitle, String musicDescription, TagBaseEntity tagBase) {
        this.musicTitle = musicTitle;
        this.musicDescription = musicDescription;
        this.createdAt = LocalDateTime.now();
        this.tagId = tagBase;
    }

    public TagMusicEntity updateTagMusic(final String musicTitle, final String musicDescription) {
        this.musicTitle = musicTitle;
        this.musicDescription = musicDescription;

        return this;
    }
}
