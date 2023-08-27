package com.spottag.app.controller;

import com.spottag.app.service.spotify.SpotifyServiceImpl;
import com.spottag.app.service.spotify.dto.SpotifyMusicTrackDto;
import com.spottag.app.service.tag.TagMusicServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ResourceBundle;


@Slf4j
@RestController
@Profile("dev")
@RequestMapping("/test")
@RequiredArgsConstructor
@Tag(name = "Test", description = "API Test only can be Generated with Dev Profile")
public class TestController {
    private final SpotifyServiceImpl spotifyService;

    @GetMapping("/music")
    public ResponseEntity<List<SpotifyMusicTrackDto>> testSearchMusic(@RequestParam String musicName) throws Exception {
        return ResponseEntity.ok().body(spotifyService.searchTrackByTrackName(musicName, 1, 10));
    }
}
