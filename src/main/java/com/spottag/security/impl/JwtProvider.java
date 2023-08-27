package com.spottag.security.impl;

import com.spottag.security.AuthToken;
import com.spottag.security.AuthTokenProvider;
import com.spottag.security.JwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Component
public class JwtProvider implements AuthTokenProvider<JwtToken> {

    @Value("${jwt.secret}")
    private String secret;
    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public JwtToken createToken(Long accountId, String role, Map<String, Object> claims, Date expiredDate) {
        return new JwtToken(accountId.toString(), key, role, claims, expiredDate);
    }

    @Override
    public JwtToken convertAuthToken(String token) {
        return new JwtToken(token, key);
    }

    @Override
    public Authentication getAuthentication(JwtToken authToken) {

        if (authToken.validate()) {

            Claims claims = authToken.getDate();

            Set<SimpleGrantedAuthority> authorities = Collections.singleton(
                    new SimpleGrantedAuthority(claims.get(AuthToken.AUTHORITIES_TOKEN_KEY, String.class)));

            User principal = new User(claims.getSubject(), "", authorities);

            return new UsernamePasswordAuthenticationToken(principal, authToken, authorities);
        } else {
            throw new JwtException("token Error");
        }
    }

    public String getUserId(final String token) {

        final JwtToken authToken = this.convertAuthToken(token);

        return authToken.getDate().getSubject();
    }

}
