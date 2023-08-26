package com.spottag.app.service.tag.dto;

import com.spottag.app.domain.model.entity.TagBaseEntity;
import com.spottag.app.domain.model.entity.TagImageEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@SuperBuilder
public class TagImageDto {
    private final Long tagImageId;
    private final LocalDateTime createdAt;


    public static TagImageDto ofEntity(TagImageEntity entity) {
        return TagImageDto.builder()
                .tagImageId(entity.getTagImageId())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
