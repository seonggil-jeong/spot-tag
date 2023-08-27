package com.spottag.app.controller.tag.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UpdateTagBaseRequest {
    @Schema(example = "tagContent", description = "내용")
    private final String tagContent;
}
