package com.spottag.app.domain.repository;

import com.spottag.app.domain.model.entity.TagBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagBaseRepository extends JpaRepository<TagBaseEntity, Long> {

    Optional<TagBaseEntity> findByTagIdAndDeletedAtIsNull(Long tagId);
}
