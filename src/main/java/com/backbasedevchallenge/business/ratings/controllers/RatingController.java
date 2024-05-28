package com.backbasedevchallenge.business.ratings.controllers;

import com.backbasedevchallenge.business.ratings.dto.RatingRequestDto;
import com.backbasedevchallenge.business.ratings.dto.RatingResponseDto;
import com.backbasedevchallenge.business.ratings.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingResponseDto> addMovieRatting(@RequestBody RatingRequestDto ratingRequest) {
        var rating = ratingService.addMovieRatting(ratingRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RatingResponseDto.builder()
                        .movieTitle(rating.movieTitle())
                        .ratedBy(rating.ratedBy())
                        .build());
    }

    @PutMapping
    public ResponseEntity<RatingResponseDto> updateMovieRatting(@RequestBody RatingRequestDto ratingRequest) {
        var rating = ratingService.updateMovieRatting(ratingRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RatingResponseDto.builder()
                        .movieTitle(rating.movieTitle())
                        .ratedBy(rating.ratedBy())
                        .build());
    }
}
