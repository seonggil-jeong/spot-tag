package com.spottag.app.controller.tag;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.controller.tag.res.TagBaseDetailResponse;
import com.spottag.app.controller.tag.res.TagBaseResponse;
import com.spottag.app.service.tag.TagBaseServiceImpl;
import com.spottag.app.service.tag.TagFacadeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
@Tag(name = "Tag Main", description = "Tag 정보 조회 및 Music, Image 함께 수정 삭제 API")
public class TagController extends ControllerSupport {
    private final TagFacadeServiceImpl tagFacadeService;
    private final TagBaseServiceImpl tagBaseService;

    @GetMapping("/tags/meta")
    @Operation(summary = "TagBase 정보만 조회", description = "위치 기반 Tag BaseList 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<List<TagBaseResponse>> getTagBaseList(
            @Parameter(description = "위도", required = true)
            @RequestParam final String latitude,
            @Parameter(description = "경도", required = true)
            @RequestParam final String longitude
    ) throws Exception {

        return ResponseEntity.ok().build();

    }

    @GetMapping("/tags/{tagId}/details")
    @Operation(summary = "TagBase 정보만 조회", description = "위치 기반 Tag BaseList 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "일치하는 Tag 정보를 찾을 수 없음")
    })
    public ResponseEntity<TagBaseDetailResponse> getTagBaseDetails(
            @Parameter(description = "tagBaseId")
            @PathVariable final Long tagId
    ) throws Exception {


        return ResponseEntity.ok().body(tagFacadeService.getTagDetailByTagId(tagId));
    }
}
