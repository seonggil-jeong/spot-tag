package com.spottag.app.controller.tag.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spottag.app.service.tag.dto.TagMusicDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class TagMusicResponse {
    private final Long tagMusicId;
    private final String title;
    private final String artistName;
    private final String trackId;
    private final String trackUrl;
    private final String previewUrl;
    private final String lImageUrl;
    private final String mImageUrl;
    private final String sImageUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;


    public static TagMusicResponse ofDto(TagMusicDto dto) {
        return TagMusicResponse.builder()
                .tagMusicId(dto.getTagMusicId())
                .title(dto.getTitle())
                .artistName(dto.getArtistName())
                .trackId(dto.getTrackId())
                .trackUrl(dto.getTrackUrl())
                .previewUrl(dto.getPreviewUrl())
                .lImageUrl(dto.getLImageUrl())
                .mImageUrl(dto.getMImageUrl())
                .sImageUrl(dto.getSImageUrl())
                .build();


    }
}
