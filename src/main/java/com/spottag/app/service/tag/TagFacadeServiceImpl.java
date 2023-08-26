package com.spottag.app.service.tag;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * (TagBase, TagImage, TagMusic) Transactional 처리
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TagFacadeServiceImpl {
    private final TagBaseServiceImpl tagBaseService;
    private final TagImageServiceImpl tagImageService;
    private final TagMusicServiceImpl tagMusicService;
}
