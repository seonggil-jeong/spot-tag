package com.spottag.app.domain.repository;

import com.spottag.app.domain.model.entity.TagMusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMusicRepository extends JpaRepository<TagMusicEntity, Long> {
}
