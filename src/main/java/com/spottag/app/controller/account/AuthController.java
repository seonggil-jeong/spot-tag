package com.spottag.app.controller.account;

import com.spottag.app.controller.ControllerSupport;
import com.spottag.app.controller.account.req.LoginRequest;
import com.spottag.app.controller.account.req.RegisterAccountRequest;
import com.spottag.app.controller.account.res.LoginResponse;
import com.spottag.app.service.account.AuthAccountService;
import com.spottag.app.service.account.dto.LoginDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "사용자 회원가입, 로그인")
public class AuthController extends ControllerSupport {
    private final AuthAccountService authAccountService;

    @Operation(summary = "사용자 로그인", description = "로그인 성공 시 토큰 발급 {TOKEN : value}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "400", description = "비밀번호 확인"),
            @ApiResponse(responseCode = "404", description = "일치하는 사용자 정보를 찾을 수 없음")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> userLogin(@Validated @RequestBody LoginRequest request) throws Exception {
        return ResponseEntity.ok().body(authAccountService.login(request));
    }


    @Operation(summary = "사용자 회원가입", description = "사용자 회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "사용자 회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "이름, 닉네임 중복")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> createAccount(@Validated @RequestBody RegisterAccountRequest request)
            throws Exception {

        authAccountService.registerAccount(
                request.getAccountId(), request.getPassword(), request.getEmail(), request.getNickname()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
