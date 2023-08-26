package com.spottag.app.domain.repository;

import com.spottag.app.domain.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
}
