package com.spottag.app.service.like;

import com.spottag.app.domain.model.entity.AccountEntity;
import com.spottag.app.domain.model.entity.LikeEntity;
import com.spottag.app.domain.model.entity.TagBaseEntity;
import com.spottag.app.domain.repository.AccountRepository;
import com.spottag.app.domain.repository.LikeRepository;
import com.spottag.app.domain.repository.TagBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl {

    private final LikeRepository likeRepository;
    private final AccountRepository accountRepository;
    private final TagBaseRepository tagBaseRepository;

    /**
     * Tag 의 좋아요 수 조회
     *
     * @param tagId
     * @return
     * @throws Exception
     */
    public Integer countLikeByAccountIdAndTagId(final Long tagId) throws Exception {
        return likeRepository.countLikeEntityByTagId(tagId);

    }

    @Transactional
    public void likeCheck(String accountId, Long tagId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid accountId: " + accountId));
        TagBaseEntity tagBaseEntity = tagBaseRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tagId: " + tagId));

        Optional<LikeEntity> existingLike = likeRepository.findByAccountIdAndTagId(accountEntity, tagBaseEntity);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
        } else {
            LikeEntity newLike = LikeEntity.builder()
                    .tagBaseEntity(tagBaseEntity)
                    .accountEntity(accountEntity)
                    .build();
            likeRepository.save(newLike);
        }
    }
}
