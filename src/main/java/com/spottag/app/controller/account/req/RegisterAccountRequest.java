package com.spottag.app.controller.account.req;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.*;

import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Schema(description = "사용자 회원가입")
public class RegisterAccountRequest {
    @NotNull(message = "userId cannot be null")
    @Schema(description = "사용자 아이디", example = "userId")
    private final String accountId;

    @NotNull(message = "password cannot be null")
    @Schema(description = "사용자 비밀번호", example = "password")
    private final String password;

    @NotNull(message = "email cannot be null")
    @Email
    @Schema(description = "사용자 이메일", example = "example@example.com")
    private final String email;

    @NotNull(message = "nickname cannot be null")
    @Schema(description = "사용자 닉네임", example = "nickname")
    private final String nickname;

}

