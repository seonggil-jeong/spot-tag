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
    private final LocalDateTime createdAt;


    public static TagMusicDto ofEntity(TagMusicEntity entity) {
        return TagMusicDto.builder()
                .tagMusicId(entity.getTagMusicId())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
