package com.spottag.app.service.tag;

import com.spottag.app.domain.model.TagBaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    private String updatedBy;
    private String deletedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


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
