package com.spottag.app.domain.model;

import com.spottag.app.domain.model.enums.AccountRoleEnums;
import com.spottag.app.domain.model.listener.AccountEntityListener;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AccountEntityListener.class)
public class AccountEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private AccountRoleEnums role;

    @Nullable
    @Column(name = "deleted_by")
    private Date deletedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Setter
    @Nullable
    @Column(name = "deleted_at")
    private Date deletedAt;
}
