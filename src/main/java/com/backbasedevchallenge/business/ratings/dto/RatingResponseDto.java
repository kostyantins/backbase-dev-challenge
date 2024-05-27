package com.backbasedevchallenge.business.ratings.dto;

import lombok.Builder;

@Builder
public record RatingResponseDto(String movieTitle, int ratedBy) {
}
