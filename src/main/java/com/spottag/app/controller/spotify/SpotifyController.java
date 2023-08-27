package com.spottag.app.controller.spotify;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.controller.spotify.res.SpotifyMusicResponse;
import com.spottag.app.service.spotify.SpotifyServiceImpl;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "Spotify", description = "노래 검색")
@RequestMapping("/api/v1")
public class SpotifyController extends ControllerSupport {
    private final SpotifyServiceImpl spotifyService;


    @GetMapping("/spotify/search")
    @Operation(summary = "Spotify 음악 검색", description = "이름으로 음악 검색")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public ResponseEntity<List<SpotifyMusicResponse>> searchMusicFromSpotify(
            @Parameter(description = "Query (name)")
            @RequestParam final String q,
            @RequestParam(defaultValue = "0") final Integer offset,
            @RequestParam(defaultValue = "10") final Integer limit
    ) throws Exception {

        return ResponseEntity.ok().body(spotifyService.searchTrackByTrackName(q, offset, limit).stream().map(SpotifyMusicResponse::ofDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/spotify/search/{trackId}")
    @Operation(summary = "Spotify 음악 검색", description = "trackId로 음악 검색")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "일치하는 정보가 없음")
    })
    public ResponseEntity<SpotifyMusicResponse> searchMusicByTrackId(
            @PathVariable final String trackId
    ) throws Exception {

        return ResponseEntity.ok().body(SpotifyMusicResponse.ofDto(
                spotifyService.searchTrackByTrackId(trackId).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot found Track By Id : " + trackId)))
        );
    }

}
