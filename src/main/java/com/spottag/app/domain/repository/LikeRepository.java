package com.spottag.app.domain.repository;


import com.spottag.app.domain.model.LikeEntity;
import com.spottag.app.domain.model.TagBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    Optional<LikeEntity> findByAccountIdAndTagId(Long accountId, Long tagId);

    Integer countLikeEntityByTagId(final TagBaseEntity tagBaseEntity);
}
