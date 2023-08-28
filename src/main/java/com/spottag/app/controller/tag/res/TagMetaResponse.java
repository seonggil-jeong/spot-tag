package com.spottag.app.controller.tag.res;

import com.spottag.app.service.tag.dto.TagInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class TagMetaResponse {
    @Schema(description = "묶인 Tag가 표시될 위도")
    private final String latitude;
    @Schema(description = "묶인 Tag가 표시될 경도")
    private final String longitude;
    @Schema(description = "묶인 Tag 목록")
    private final List<TagInfoResponse> tags;
}
