package com.spottag.app.service.tag;

import com.spottag.app.domain.repository.TagMusicRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagMusicServiceImpl {
    private final TagMusicRepository tagMusicRepository;
}
