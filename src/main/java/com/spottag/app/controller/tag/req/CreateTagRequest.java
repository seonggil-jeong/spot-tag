package com.spottag.app.controller.tag.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class CreateTagRequest {
    @Schema(description = "TagBase")
    private final CreateTagBaseRequest tagBase;
    @Schema(description = "Music TrackId")
    private final String trackId;
}
