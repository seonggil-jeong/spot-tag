package com.spottag.app.service.like;

import com.spottag.app.domain.model.AccountEntity;
import com.spottag.app.domain.model.LikeEntity;

import com.spottag.app.domain.model.TagBaseEntity;
import com.spottag.app.domain.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Transactional
    public void likecheck(Long userId, Long tagId) {
        Optional<LikeEntity> existingLike = likeRepository.findByAccountIdAndTagId(userId, tagId);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
        } else {
            LikeEntity newLike = LikeEntity.builder()
                    .likeDate(LocalDateTime.now())
                  //  .accountId(new AccountEntity(userId))
                  //  .tagId(new TagBaseEntity(tagId))
                    .build();
            likeRepository.save(newLike);
        }
    }
}
