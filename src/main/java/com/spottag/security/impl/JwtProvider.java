package com.spottag.security.impl;

import com.spottag.security.AuthToken;
import com.spottag.security.AuthTokenProvider;
import com.spottag.security.JwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtProvider implements AuthTokenProvider<JwtToken> {

    private final RedisTemplate<String, String> redisTemplate;


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.access-expires}")
    private long accessExpires;

    @Value("${jwt.token.refresh-expires}")
    private long refreshExpires;

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

    /**
     * access token 만료 시간은 현재 시간 + 20분이다.
     */
    @Override
    public JwtToken createAccessToken(Long accountId, String role, Map<String, Object> claims) {
        return new JwtToken(accountId.toString(), key, role, claims, new Date(System.currentTimeMillis() + 1000 * 60 * 20));

    }

    /**
     * refresh token 만료 시간은 현재 시간 + 2일이다.
     */
    @Override
    public JwtToken createRefreshToken(Long accountId, String role, Map<String, Object> claims) {
        JwtToken jwtToken = new JwtToken(accountId.toString(), key, role, claims, new Date(System.currentTimeMillis() + 1000 * 60 * 20));
        redisTemplate.opsForValue().set(accountId.toString(), jwtToken.getToken(), refreshExpires, TimeUnit.SECONDS);
        return jwtToken;
    }

    public String getUserId(final String token) {

        final JwtToken authToken = this.convertAuthToken(token);

        return authToken.getDate().getSubject();
    }

}
