package com.spottag.app.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountConstants {
    public static final String AUTHORIZATION_TOKEN_KEY = "Authorization";

    public static final String REFRESH_TOKEN_TYPE_VALUE = "refresh_token";
    public static final String ACCESS_TOKEN_TYPE_VALUE = "access_token";
}
