package com.spottag.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.extern.slf4j.Slf4j;

import javax.security.auth.login.AccountException;

/**
 * Spring Controller Support (handle Security & Token)
 */
@Slf4j
public class ControllerSupport {


    /**
     * Spring 인증 조회
     *
     * @return
     */
    private final Authentication getAuthentication() throws AccountException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AccountException("Can not get authentication.");
        }

        return authentication;
    }

    /**
     * 로그인 사용자 아이디 조회
     * @return
     */
    public Integer getUserId() throws AccountException{
        String username = getAuthentication().getName();
        if(username == null){
            throw new AccountException("Can not get username.");
        }

        return Integer.valueOf(username);
    }
}

