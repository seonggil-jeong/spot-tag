package com.spottag.app.controller.tag;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.service.tag.TagFacadeServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
@Tag(name = "Tag Main", description = "TagBase, Music, Image 함께 생성 시 사용")
public class TagController extends ControllerSupport {
    private final TagFacadeServiceImpl tagFacadeService;

    @GetMapping("/tags")
    public ResponseEntity<Void> tr() {
        return ResponseEntity.ok().build();
    }
}
