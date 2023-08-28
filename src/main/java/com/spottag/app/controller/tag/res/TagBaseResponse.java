package com.spottag.app.controller.tag.res;

import com.spottag.app.service.tag.dto.TagBaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;


@RequiredArgsConstructor
@Getter
@SuperBuilder
@NoArgsConstructor(force = true)
public class TagBaseResponse {
    @Schema(description = "tag Id")
    private final Long tagId;

    @Schema(description = "위도")
    private final String latitude;

    @Schema(description = "경도")
    private final String longitude;

    @Schema(description = "내용")
    private final String tagContent;

    @Schema(description = "좋아요 수")
    private Integer likeCount;

    @Schema(description = "이미지 보유 여부")
    private Boolean hasImage;

    @Schema(description = "음악 정보 보유 여부")
    private Boolean hasMusic;


    public static TagBaseResponse ofDto(TagBaseDto dto) {
        return TagBaseResponse.builder()
                .tagId(dto.getTagId())
                .tagContent(dto.getTagContent())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .likeCount(dto.getLikeCount())
                .hasImage(dto.getTagMusicId() != null)
                .build();
    }
}
