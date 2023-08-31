package com.spottag.app.service.account;

import com.mchange.util.DuplicateElementException;
import com.spottag.app.controller.account.req.LoginRequest;
import com.spottag.app.controller.account.res.LoginResponse;
import com.spottag.app.domain.model.entity.AccountEntity;
import com.spottag.app.domain.repository.AccountRepository;
import com.spottag.app.service.account.dto.AccountDto;
import com.spottag.app.service.account.dto.LoginDto;
import com.spottag.enums.AccountRoleEnums;
import com.spottag.exception.CustomException;
import com.spottag.exception.enums.ExceptionEnum;
import com.spottag.security.AuthTokenProvider;
import com.spottag.security.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.spottag.utils.crypto.Password.verifyPassword;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AuthAccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenProvider authTokenProvider;

    // -----> Auth AccountService

    /**
     * 사용자 회원가입
     *
     * @param accountId
     * @param password
     * @param email
     * @param nickname
     * @return
     * @throws Exception
     */
    @Override
    public AccountDto registerAccount(String accountId, String password, String email, String nickname) throws Exception {

        return AccountDto.ofEntity(accountRepository.save(AccountEntity.builder()
                .accountId(getNotDuplicatedAccountId(accountId))
                .role(AccountRoleEnums.USER)
                .email(getNotDuplicatedAccountEmail(email))
                .nickname(nickname) // 중복된 닉네임 처리 방법 필요
                .password(passwordEncoder.encode(password))
                .build()));

    }

    private String getNotDuplicatedAccountEmail(final String email) {
        if (!this.isUsedAccountEmail(email)) {
            return email;
        } else {
            throw new DuplicateElementException(email + " email is already used");
        }
    }

    private String getNotDuplicatedAccountId(final String accountId) {
        if (!this.isUsedAccountId(accountId)) {
            return accountId;

        } else {
            throw new DuplicateElementException(accountId + " accountId is already used");
        }

    }


    private boolean isUsedAccountEmail(final String email) {
        return accountRepository.findFirstByEmailAndDeletedByIsNull(email).isPresent();

    }

    private boolean isUsedAccountId(final String accountId) {
        return accountRepository.findFirstByAccountIdAndDeletedByIsNull(accountId).isPresent();
    }

    @Override
    public LoginResponse login(LoginRequest request) throws Exception {
        AccountEntity account = accountRepository.findById(request.getAccountId()).
                orElseThrow(() -> new CustomException(ExceptionEnum.BAD_REQUEST));

        if (!verifyPassword(request.getPassword(), account.getPassword())) {
            throw new CustomException(ExceptionEnum.BAD_REQUEST);
        }

        Map<String, Object> claims = Map.of("accountId", account.getAccountId(), "role", account.getRole());
        JwtToken accessToken = (JwtToken) authTokenProvider.createAccessToken(Long.valueOf(account.getAccountId()), account.getRole().name(), claims);
        JwtToken refreshToken = (JwtToken) authTokenProvider.createRefreshToken(Long.valueOf(account.getAccountId()), account.getRole().name(), claims);

        return LoginResponse.ofDto(accessToken.getToken(), refreshToken.getToken());
    }

}
