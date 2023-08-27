package com.spottag.app.controller.tag;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.service.tag.TagMusicServiceImpl;
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
@Tag(name = "Tag Music", description = "Tag Music")
public class TagMusicController extends ControllerSupport {
    private final TagMusicServiceImpl tagMusicService;

    @PostMapping("/tags/{tagId}/music")
    @Operation(summary = "Tag Music 등록", description = "Tag Music 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "404", description = "Track 정보를 찾을 수 없음")
    })
    public ResponseEntity<Void> createTagMusic(
            @Parameter(description = "tagBaseId")
            @PathVariable final Long tagId,
            @Parameter(description = "Spotify TrackId")
            @RequestParam final String trackId
    ) throws Exception {

        tagMusicService.createTagMusic(getAccountId(), tagId, trackId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/tags/{tagId}/music")
    @Operation(summary = "TagMusic 정보 변경", description = "해당 TagBase가 Music 정보를 가지고 있다면 변경")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "변경 선공"),
            @ApiResponse(responseCode = "404", description = "일치하는 TagBase 정보를 찾을 수 없음"),
            @ApiResponse(responseCode = "400", description = "해당 Tag는 Music 정보를 가지고 있지 않음")
    })
    public ResponseEntity<Void> updateTagMusic(
            @Parameter(description = "tagBaseId")
            @PathVariable final Long tagId,
            @Parameter(description = "Spotify TrackId")
            @RequestParam final String trackId)
            throws Exception {

        tagMusicService.updateTagMusic(getAccountId(), tagId, trackId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/tags/{tagId}/music")
    @Operation(summary = "TagMusic 삭제", description = "해당 TagBase Id에 있는 음악 정보 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 선공"),
            @ApiResponse(responseCode = "404", description = "TagBase를 찾을 수 없음"),
            @ApiResponse(responseCode = "403", description = "해당 유저가 작성한 Tag 정보가 아님")
    })
    public ResponseEntity<Void> deleteTagMusic(
            @Parameter(description = "tagBaseId")
            @PathVariable final Long tagId
    ) throws Exception {

        tagMusicService.deleteTagMusic(getAccountId(), tagId);


        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
