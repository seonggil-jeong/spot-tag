package com.spottag.app.service.tag;

import com.spottag.app.domain.model.entity.TagBaseEntity;
import com.spottag.app.domain.repository.TagBaseRepository;
import com.spottag.app.service.like.LikeServiceImpl;
import com.spottag.app.service.tag.dto.TagBaseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

/**
 * Tag Base Service
 *
 * @since 2023. 08. 27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TagBaseServiceImpl {
    private final TagBaseRepository tagBaseRepository;
    private final LikeServiceImpl likeServiceImpl;


    /**
     * Tag Base 정보 조회
     *
     * @param tagId
     * @return
     */
    public TagBaseDto getTagBaseByTagId(final Long tagId) throws Exception {
        TagBaseDto tagBaseDto = TagBaseDto.ofEntity(tagBaseRepository.findByTagIdAndDeletedAtIsNull(tagId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot found TagBase with Id : " + tagId)));
        tagBaseDto.setLikeCount(likeServiceImpl.countLikeByAccountIdAndTagId(tagId));

        return tagBaseDto;
    }


    /**
     * Tag Base 생성
     *
     * @param accountId  생성자 아이디
     * @param tagContent 내용
     * @param latitude   위도
     * @param longitude  경도
     * @return
     * @throws Exception
     */
    @Transactional(rollbackOn = Exception.class)
    public TagBaseDto createTagBase(
            final String accountId,
            final String tagContent, final String latitude, final String longitude
    ) throws Exception {

        return TagBaseDto.ofEntity(tagBaseRepository.save(TagBaseEntity.builder()
                .tagContent(tagContent)
                .latitude(latitude)
                .longitude(longitude)
                .createdBy(accountId)
                .build()));
    }

    /**
     * Update TagUd
     *
     * @param accountId
     * @param tagId
     * @param tagContent
     * @return
     * @throws Exception
     */
    @Transactional(rollbackOn = Exception.class)
    public TagBaseDto updateTagBase(final String accountId, final Long tagId, final String tagContent) throws Exception {

        return TagBaseDto.ofEntity(tagBaseRepository.save(
                getValidatedTagBaseEntityById(tagId, accountId).updateTagBase(tagContent, accountId))
        );

    }


    /**
     * Delete Tag Base By Id
     *
     * @param tagBaseId         targetId
     * @param deleteByAccountId delete By
     * @return deleted TagBase Info
     * @throws NoSuchElementException cannot found TagBase By Id
     */
    @Transactional(rollbackOn = Exception.class)
    public TagBaseDto deleteTagBaseByTagId(final Long tagBaseId, final String deleteByAccountId) throws Exception {

        return TagBaseDto.ofEntity(tagBaseRepository.save(
                        getValidatedTagBaseEntityById(tagBaseId, deleteByAccountId)
                ).deleteTagBase(deleteByAccountId)
        );
    }


    /**
     * CUD 작업 시 Validated Entity 로직
     *
     * @param tagBaseId tagBaseId
     * @param accountId accountId
     * @return
     * @throws ResponseStatusException 일치하는 정보를 찾을 수 없음
     * @throws ResponseStatusException 해당 유저가 생성한 tag가 아님
     */
    private TagBaseEntity getValidatedTagBaseEntityById(final Long tagBaseId, final String accountId) {
        TagBaseEntity tagBase = tagBaseRepository.findByTagIdAndDeletedAtIsNull(tagBaseId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot found TagBase with Id : " + tagBaseId));

        if (!tagBase.getAccountId().getAccountId().equals(accountId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "cannot access this Resources");
        }

        return tagBase;
    }
}
