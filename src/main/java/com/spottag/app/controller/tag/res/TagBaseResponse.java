package com.spottag.app.controller.tag.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Getter
@Builder
@NoArgsConstructor(force = true)
public class TagBaseResponse {
    @Schema(description = "tag Id")
    private final Long tagId;

    @Schema(description = "내용")
    private final String tagContent;

    @Schema(description = "생성자")
    private final String createdBy;

    @Schema(description = "생성일")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @Schema(description = "수정일")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime updatedAt;

    @Schema(description = "삭제일")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime deletedAt;

    @Schema(description = "좋아요 수")
    private final Integer likeCount;
}
