package com.spottag.app.service.tag.dto;

import com.spottag.app.domain.model.entity.TagBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@SuperBuilder
public class TagBaseDto {
    private final Long tagId;
    private final String tagContent;
    private final String createdBy;
    private final String updatedBy;
    private final String deletedBy;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;

    private Integer likeCount;


    public static TagBaseDto ofEntity(TagBaseEntity entity) {
        return TagBaseDto.builder()
                .tagId(entity.getTagId())
                .tagContent(entity.getTagContent())
                .createdBy(entity.getCreatedBy())
                .updatedBy(entity.getUpdatedBy())
                .deletedBy(entity.getDeletedBy())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt()).build();
    }
}
