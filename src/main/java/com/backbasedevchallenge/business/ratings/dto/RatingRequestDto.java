package com.backbasedevchallenge.business.ratings.dto;

import com.backbasedevchallenge.business.ratings.enums.MovieRatings;
import lombok.Builder;

@Builder
public record RatingRequestDto(Long movieId, MovieRatings rating) {
}
