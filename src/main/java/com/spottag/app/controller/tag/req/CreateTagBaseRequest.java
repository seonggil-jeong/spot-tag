package com.spottag.app.controller.tag.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateTagBaseRequest {
    @Schema(description = "내용")
    private String tagContent;

    @Schema(description = "위도")
    private String latitude;

    @Schema(description = "경도")
    private String longitude;
}
