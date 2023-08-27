package com.spottag.app.controller.tag;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.controller.tag.req.UpdateTagBaseRequest;
import com.spottag.app.service.tag.TagBaseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@Tag(name = "Tag Base", description = "이미지, 음악 없이 base Handle")
public class TagBaseController extends ControllerSupport {
    private final TagBaseServiceImpl tagBaseService;

    @GetMapping("/tags/base")
    @Operation(summary = "TagBase 정보 조회", description = "위치 기반 Tag BaseList 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<Void> getTagBaseList(
            @Parameter(description = "위도", required = true)
            @RequestParam final String latitude,
            @Parameter(description = "경도", required = true)
            @RequestParam final String longitude
    ) throws Exception {

        return ResponseEntity.ok().build();

    }

    @PatchMapping("/tags/{tagId}/base")
    @Operation(summary = "TagBase 정보 수정", description = "TagBase 정보만 수정할 경우")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "404", description = "일치하는 TagBase 정보를 찾을 수 없음"),
            @ApiResponse(responseCode = "403", description = "해당 유저가 작성한 Tag가 아님")
    })
    public ResponseEntity<Void> updateTagBase(
            @Parameter(example = "1", description = "tagBaseId", required = true)
            @PathVariable final Long tagId,
            @RequestBody final UpdateTagBaseRequest body

    ) throws Exception {

        return ResponseEntity.ok().build();

    }

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
