package com.spottag.app.service.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor(force = true)
@Builder
public class LoginDto {
    private final String email;
    private final String password;

    public static LoginDto of(String email, String password) {
        return LoginDto.builder()
                .email(email)
                .password(password)
                .build();
    }
}
