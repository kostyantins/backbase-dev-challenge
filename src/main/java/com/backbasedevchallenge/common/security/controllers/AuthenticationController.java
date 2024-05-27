package com.backbasedevchallenge.common.security.controllers;

import com.backbasedevchallenge.common.security.configurations.JwtUtils;
import com.backbasedevchallenge.common.security.configurations.SecurityConfig;
import com.backbasedevchallenge.common.security.dto.AuthenticationRequestDto;
import com.backbasedevchallenge.common.security.dto.AuthenticationResponseDto;
import com.backbasedevchallenge.business.users.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final SecurityConfig securityConfig;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequestDto authenticationRequest) {
        securityConfig.authenticationProvider().authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()));

        final var userDetails = userDao.loadUserByEmail(authenticationRequest.email());

        if (userDetails != null) {
            return ResponseEntity.ok().body(AuthenticationResponseDto.builder()
                    .apiKey(jwtUtils.generateToken(userDetails))
                    .build());
        }

        return ResponseEntity.status(400).body("Something wrong with the user %s".formatted(userDetails));
    }

}
