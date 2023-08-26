package com.spottag.app.domain.repository;

import com.spottag.app.domain.model.entity.LikeEntity;
import com.spottag.app.domain.model.entity.TagBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    Integer countLikeEntityByTagId(final TagBaseEntity tagBaseEntity);
}