package com.spottag.app.domain.repository;

import com.spottag.app.domain.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findFirstByAccountIdAndDeletedByIsNull(final String accountId);
    Optional<AccountEntity> findFirstByEmailAndDeletedByIsNull(final String email);
}
