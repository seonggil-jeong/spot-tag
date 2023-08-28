package com.spottag.app.service.tag.dto;

import com.spottag.app.domain.model.entity.TagBaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@SuperBuilder
public class TagBaseDto {
    private final Long tagId;
    private final String tagContent;
    private final String latitude;
    private final String longitude;
    private final String accountId;
    private final String createdBy;
    private final String updatedBy;
    private final String deletedBy;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;

    private final Long tagMusicId;
    @Setter
    private Integer likeCount;


    public static TagBaseDto ofEntity(TagBaseEntity entity) {
        return TagBaseDto.builder()
                .tagId(entity.getTagId())
                .tagContent(entity.getTagContent())
                .accountId(entity.getAccountId().getAccountId())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .deletedBy(entity.getDeletedBy())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .tagMusicId(entity.getTagMusic().getTagMusicId())
                .build();
    }
}
