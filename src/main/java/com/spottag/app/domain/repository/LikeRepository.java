package com.spottag.app.domain.repository;

import com.spottag.app.domain.model.entity.AccountEntity;
import com.spottag.app.domain.model.entity.LikeEntity;
import com.spottag.app.domain.model.entity.TagBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    Optional<LikeEntity> findByAccountIdAndTagId(AccountEntity accountId, TagBaseEntity tagId);

    @Query(value = " "
            + " select count(1)"
            + " from like_info as t1 where t1.account_id = :accountId and t1.tag_id = :tagId", nativeQuery = true)
    Integer countLikeEntityByTagIdAndAccountId(final String accountId, final Long tagId);

    @Query(value = " "
            + " select count(1)"
            + " from like_info as t1 where t1.tag_id = :tagId", nativeQuery = true)
    Integer countLikeEntityByTagId(final Long tagId);
}
