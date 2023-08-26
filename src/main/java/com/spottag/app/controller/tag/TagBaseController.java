package com.spottag.app.controller.tag;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.service.tag.TagBaseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Tag")
public class TagBaseController extends ControllerSupport {
    private final TagBaseServiceImpl tagBaseService;

    @DeleteMapping("/tags/base/{tagId}")
    @Operation(summary = "TagBase 정보 삭제", description = "Tag Base 정보 삭제 시 해당 정보는 출력 안됨")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "아이디 값과 일치하는 Base 정보를 찾을 수 없음")
    })
    public ResponseEntity<Void> deleteTagById(
            @PathVariable final Long tagId
    ) throws Exception {
        tagBaseService.deleteTagBaseByTagId(tagId, "test");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
