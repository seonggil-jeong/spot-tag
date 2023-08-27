package com.spottag.app.service.tag;

import com.spottag.app.controller.tag.res.TagBaseDetailResponse;
import com.spottag.app.controller.tag.res.TagMusicResponse;
import com.spottag.app.service.tag.dto.TagBaseDto;
import com.spottag.app.service.tag.dto.TagMusicDto;
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


    public TagBaseDetailResponse getTagDetailByTagId(final Long tagId) throws Exception {
        final TagBaseDto tagBase = tagBaseService.getTagBaseByTagId(tagId);
        final TagMusicDto tagMusic = tagMusicService.getTagMusicByTagMusicId(tagBase.getTagMusicId());

        return TagBaseDetailResponse.mergeTagBaseAndTagMusic(tagBase, tagMusic);
    }
}
