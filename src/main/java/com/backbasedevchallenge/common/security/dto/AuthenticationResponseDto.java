package com.backbasedevchallenge.common.security.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponseDto(String apiKey) {
}
