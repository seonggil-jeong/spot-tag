package com.spottag.app.controller.tag;

import com.spottag.app.service.tag.TagFacadeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TagController {
    private final TagFacadeServiceImpl tagFacadeService;
}
