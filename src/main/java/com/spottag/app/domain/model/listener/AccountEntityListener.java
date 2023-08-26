package com.spottag.app.domain.model.listener;

import com.spottag.app.domain.model.AccountEntity;
import jakarta.persistence.PreUpdate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AccountEntityListener {

    @PreUpdate
    public void preUpdate(AccountEntity accountEntity) {
        if (accountEntity.getDeletedBy() != null) {
            accountEntity.setDeletedAt(new Date());
        }
    }
}
