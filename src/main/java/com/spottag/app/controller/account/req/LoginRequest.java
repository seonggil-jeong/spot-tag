package com.spottag.app.controller.account.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class LoginRequest {
    private final String accountId;
    private final String password;
}
