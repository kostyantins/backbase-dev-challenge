package com.backbasedevchallenge.common.security.dto;

import lombok.Builder;

@Builder
public record AuthenticationRequestDto(String email, String password) {
}
