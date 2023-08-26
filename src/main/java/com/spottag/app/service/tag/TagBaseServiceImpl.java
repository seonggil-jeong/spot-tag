package com.spottag.app.service.tag;

import com.spottag.app.domain.repository.TagBaseRepository;
import com.spottag.app.service.tag.dto.TagBaseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

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
                tagBaseRepository.findById(tagBaseId).orElseThrow(
                        () -> new NoSuchElementException("cannot found TagBase Info with Id : " + tagBaseId)
                ).deleteTagBase(deleteByAccountId)
        ));
    }
}
