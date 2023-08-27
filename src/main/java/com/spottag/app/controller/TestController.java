package com.spottag.app.controller;

import com.spottag.app.service.spotify.SpotifyServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Profile("dev")
@RequestMapping("/test")
@RequiredArgsConstructor
@Tag(name = "Test", description = "API Test only can be Generated with Dev Profile")
public class TestController {
    private final SpotifyServiceImpl spotifyService;
}
