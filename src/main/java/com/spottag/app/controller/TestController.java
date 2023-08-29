package com.spottag.app.controller;

import com.spottag.app.service.spotify.SpotifyServiceImpl;
import com.spottag.security.AuthTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;


@Slf4j
@RestController
@Profile("dev")
@RequestMapping("/test")
@RequiredArgsConstructor
@Tag(name = "Test", description = "API Test only can be Generated with Dev Profile")
public class TestController {
    private final SpotifyServiceImpl spotifyService;
    private final AuthTokenProvider authTokenProvider;

    @GetMapping("/token")
    public String test() {
        String tt = "hihi";

        Map<String, Object> testMap = new HashMap<>();
        testMap.put("testKey", "testValue");
        return authTokenProvider.createAccessToken(1L, "test", testMap);

    }
}