package com.spottag.app.service.tag.dto;

import com.spottag.app.domain.model.entity.TagBaseEntity;
import com.spottag.app.domain.model.entity.TagMusicEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@SuperBuilder
public class TagMusicDto {
    private final Long tagMusicId;
    private final String title;
    private String artistName;
    private String trackId;
    private String trackUrl;
    private String previewUrl;
    private String lImageUrl;
    private String mImageUrl;
    private String sImageUrl;
    private final LocalDateTime createdAt;


    public static TagMusicDto ofEntity(TagMusicEntity entity) {
        return TagMusicDto.builder()
                .tagMusicId(entity.getTagMusicId())
                .title(entity.getMusicTitle())
                .artistName(entity.getArtistName())
                .trackId(entity.getTrackId())
                .trackUrl(entity.getTrackUrl())
                .previewUrl(entity.getPreviewUrl())
                .lImageUrl(entity.getLImageUrl())
                .mImageUrl(entity.getMImageUrl())
                .sImageUrl(entity.getSImageUrl())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
