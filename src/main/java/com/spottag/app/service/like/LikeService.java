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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final AccountRepository accountRepository;
    private final TagBaseRepository tagBaseRepository;

    @Transactional
    public void likecheck(String accountId, Long tagId) {
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
