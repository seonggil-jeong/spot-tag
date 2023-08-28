package com.spottag.app.controller.tag.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spottag.app.service.tag.dto.TagInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Builder
public class TagInfoResponse {
    private final Long tagId;
    private final String tagContent;
    @Schema(description = "위도")
    private final String latitude;
    @Schema(description = "경도")
    private final String longitude;
    private final String accountId;
    private final String createdBy;
    private final String updatedBy;
    private final String deletedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime deletedAt;
    @Schema(description = "음악 정보 보유 여부")
    private final Boolean hasMusic;
    private final TagMusicResponse tagMusic;

    public static TagInfoResponse ofDto(final TagInfoDto dto) {
        return TagInfoResponse.builder()
                .tagId(dto.getTagId())
                .tagContent(dto.getTagContent())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .accountId(dto.getAccountId())
                .createdBy(dto.getCreatedBy())
                .updatedBy(dto.getUpdatedBy())
                .deletedBy(dto.getDeletedBy())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .hasMusic(dto.getHasMusic())
                .tagMusic(TagMusicResponse.builder()
                        .tagMusicId(dto.getTagMusicId())
                        .createdAt(dto.getMusicCreatedAt())
                        .title(dto.getMusicTitle())
                        .artistName(dto.getArtistName())
                        .previewUrl(dto.getPreviewUrl())
                        .trackId(dto.getTrackId())
                        .trackUrl(dto.getTrackUrl())
                        .lImageUrl(dto.getLImageUrl())
                        .mImageUrl(dto.getMImageUrl())
                        .sImageUrl(dto.getSImageUrl())
                        .build())
                .build();
    }
}
