package com.backbasedevchallenge.business.ratings.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovieRatings {

    NEGATIVE(1),
    FAIR(2),
    GOOD(3),
    EXCELLENT(4),
    PERFECT(5);

    private final int rating;
}
