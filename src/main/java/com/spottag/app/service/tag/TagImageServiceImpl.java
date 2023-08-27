package com.spottag.app.service.tag;

import com.spottag.app.domain.repository.TagImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagImageServiceImpl {
    private final TagImageRepository tagImageRepository;
}
