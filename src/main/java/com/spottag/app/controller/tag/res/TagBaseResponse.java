package com.spottag.app.controller.tag.res;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "내용")
    private final String tagContent;

    @Schema(description = "좋아요 수")
    private Integer likeCount;

    @Schema(description = "이미지 보유 여부")
    private Boolean hasImage;

    @Schema(description = "음악 정보 보유 여부")
    private Boolean hasMusic;
}
