package com.spottag.app.domain.repository;

import com.spottag.app.domain.model.entity.TagBaseEntity;
import com.spottag.app.domain.model.entity.TagMusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagMusicRepository extends JpaRepository<TagMusicEntity, Long> {

    Optional<TagMusicEntity> findByTagId(TagBaseEntity tagBaseEntity);
}
