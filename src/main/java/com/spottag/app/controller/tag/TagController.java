package com.spottag.app.controller.tag;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.controller.tag.req.CreateTagRequest;
import com.spottag.app.controller.tag.res.*;
import com.spottag.app.service.tag.TagServiceImpl;
import com.spottag.app.service.tag.dto.TagInfoDto;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
@Tag(name = "Tag Main", description = "Tag 정보 조회 및 Music, Image 함께 수정 삭제 API")
public class TagController extends ControllerSupport {
    private final TagServiceImpl tagService;

    @PostMapping("/tags")
    @Operation(summary = "Tag 등록", description = "Tag 정보 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "404", description = "일치하는 Track 노래 정보를 찾을 수 없음")
    })
    public ResponseEntity<TagInfoResponse> createTag(
            @RequestBody final CreateTagRequest body
    ) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(TagInfoResponse.ofDto(tagService.createTag(
                getAccountId(),
                body.getTagBase().getTagContent(),
                body.getTagBase().getLatitude(),
                body.getTagBase().getLongitude(),
                body.getTrackId()
        )));

    }

    @GetMapping("/tags/meta")
    @Operation(summary = "Tag Meta 정보 조회", description = "위치 기반 Tag Meta 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<List<TagMetaResponse>> getTagInfoList(
            @Parameter(description = "사용자 위도", required = true)
            @RequestParam final String latitude,
            @Parameter(description = "사용자 경도", required = true)
            @RequestParam final String longitude,
            @Parameter(description = "조회할 Tag 거리 ex.) 5 : 사용자 경도, 위도로부터 +-5에 있는 마커 정보 조회", required = false)
            @RequestParam(required = false, defaultValue = "0.1") final Double distance
    ) throws Exception {

        return ResponseEntity.ok().body(tagService.getTagInfoByLatitudeAndLongitude(latitude, longitude, distance)
                .stream().map(TagMetaResponse::ofDto).toList());

    }


    @GetMapping("/tags/{tagId}")
    @Operation(summary = "아이디를 사용하여 TagInfo 조회", description = "위치 기반 Tag BaseList 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "일치하는 Tag 정보를 찾을 수 없음")
    })
    public ResponseEntity<TagInfoResponse> getTagInfoDetails(
            @Parameter(description = "tagBaseId")
            @PathVariable final Long tagId
    ) throws Exception {


        return ResponseEntity.ok().body(TagInfoResponse.ofDto(tagService.getTagInfoByTagId(tagId)));
    }
}
