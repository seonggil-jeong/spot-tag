package com.spottag.app.service.tag;

import com.spottag.app.domain.mapper.TagMapper;
import com.spottag.app.service.like.LikeServiceImpl;
import com.spottag.app.service.tag.dto.TagBaseDto;
import com.spottag.app.service.tag.dto.TagInfoDto;
import com.spottag.app.service.tag.dto.TagMusicDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (TagBase, TagImage, TagMusic) Transactional 처리
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TagServiceImpl {
    private final TagMapper tagMapper;
    private final LikeServiceImpl likeService;
    private final TagBaseServiceImpl tagBaseService;
    private final TagMusicServiceImpl tagMusicService;


    @Transactional(rollbackOn = Exception.class)
    public TagInfoDto createTag(
            final String accountId,
            final String tagContent,
            final String latitude,
            final String longitude,
            final String trackId

    ) throws Exception {
        final TagBaseDto createdTagBase = tagBaseService.createTagBase(accountId, tagContent, latitude, longitude);
        if (trackId != null) {
            return TagInfoDto.mergeTagBaseDtoAndTagMusicDto(createdTagBase, tagMusicService.createTagMusic(
                    accountId, createdTagBase.getTagId(), trackId)
            );
        } else {
            return TagInfoDto.ofTagBaseDto(createdTagBase);
        }

    }


    /**
     * Tag Info 조회 by id
     *
     * @param tagId tagBaseId
     * @return
     * @throws Exception
     */
    public TagInfoDto getTagInfoByTagId(final Long tagId) throws Exception {
        TagInfoDto result = TagInfoDto.ofVo(tagMapper.findTagInfoByTagBaseId(tagId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot found TagBase By Id : " + tagId)));

        result.setLikeCount(likeService.countLikeByAccountIdAndTagId(tagId));

        return result;

    }

    /**
     * TagInfo 조회
     *
     * @param latitude  위도
     * @param longitude 경도
     * @param distance  +- 거리
     * @return
     * @throws Exception
     */
    public List<TagInfoDto> getTagInfoByLatitudeAndLongitude(
            final String latitude, final String longitude, Double distance
    ) throws Exception {

        return tagMapper.findAllTagInfo(distance, latitude, longitude).stream().map(TagInfoDto::ofVo)
                .map(dto -> {
                    try {
                        dto.setLikeCount(likeService.countLikeByAccountIdAndTagId(dto.getTagId()));
                        return dto;
                    } catch (Exception e) {

                        log.error("Exception : " + e);
                        return dto;
                    }
                })
                .collect(Collectors.toList());
    }
}
