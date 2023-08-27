package com.spottag.app.service.account;

import com.spottag.app.controller.account.req.LoginRequest;
import com.spottag.app.controller.account.req.RegisterAccountRequest;
import com.spottag.app.service.account.dto.AccountDto;

public interface AuthAccountService {
    /**
     * 사용자 회원가입
     * @param accountId
     * @param password
     * @param email
     * @param nickname
     * @throws Exception
     */
    AccountDto registerAccount(final String accountId, final String password, final String email, final String nickname) throws Exception;

    /**
     * 사용자 로그인
     * @param request
     * @return
     * @throws Exception
     */
    String login(LoginRequest request) throws Exception;
}
