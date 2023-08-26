package com.spottag.app.domain.repository;

import com.spottag.app.domain.model.TagBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagBaseRepository extends JpaRepository<TagBaseEntity, Long> {
}
