package com.spottag.app.controller.tag.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spottag.app.service.tag.dto.TagInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class TagMetaResponse {
    private final Long tagId;
    private final String tagContent;

    @Schema(description = "위도")
    private final String latitude;

    @Schema(description = "경도")
    private final String longitude;

    private final String accountId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;
    private final Boolean hasMusic;


    public static TagMetaResponse ofDto(final TagInfoDto dto) {
        return TagMetaResponse.builder()
                .tagId(dto.getTagId())
                .tagContent(dto.getTagContent())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .accountId(dto.getAccountId())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .hasMusic(dto.getHasMusic())
                .build();
    }
}
