package com.spottag.app.domain.repository;

import com.spottag.app.domain.model.entity.AccountEntity;
import com.spottag.app.domain.model.entity.LikeEntity;
import com.spottag.app.domain.model.entity.TagBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    Optional<LikeEntity> findByAccountIdAndTagId(AccountEntity accountId, TagBaseEntity tagId);

    Integer countLikeEntityByTagId(final TagBaseEntity tagBaseEntity);
}
