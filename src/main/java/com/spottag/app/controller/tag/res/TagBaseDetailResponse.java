package com.spottag.app.controller.tag.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spottag.app.service.tag.dto.TagBaseDto;
import com.spottag.app.service.tag.dto.TagMusicDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@SuperBuilder
public class TagBaseDetailResponse extends TagBaseResponse {

    @Schema(description = "음악 정보")
    private final TagMusicResponse tagMusicResponse;

    @Schema(description = "생성자")
    private final String createdBy;

    @Schema(description = "수정자")
    private String updatedBy;

    @Schema(description = "삭제자")
    private String deletedBy;

    @Schema(description = "생성일")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @Schema(description = "수정일")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime updatedAt;

    @Schema(description = "삭제일")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime deletedAt;

    public static TagBaseDetailResponse mergeTagBaseAndTagMusic(TagBaseDto tagBaseDto, TagMusicDto musicDto) {

        return TagBaseDetailResponse.builder()
                // from TagBaseResponse
                .tagId(tagBaseDto.getTagId())
                .likeCount(tagBaseDto.getLikeCount())
                .hasImage(false) // not yet
                .hasImage((tagBaseDto.getTagMusicId() != null))

                .tagMusicResponse(TagMusicResponse.ofDto(musicDto))
                .tagContent(tagBaseDto.getTagContent())
                .createdBy(tagBaseDto.getCreatedBy())
                .updatedBy(tagBaseDto.getUpdatedBy())
                .deletedAt(tagBaseDto.getDeletedAt())
                .createdAt(tagBaseDto.getCreatedAt())
                .updatedAt(tagBaseDto.getUpdatedAt())
                .deletedAt(tagBaseDto.getDeletedAt())
                .build();
    }
}
