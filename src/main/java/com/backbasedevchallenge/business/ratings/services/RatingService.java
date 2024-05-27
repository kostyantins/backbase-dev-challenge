package com.backbasedevchallenge.business.ratings.services;

import com.backbasedevchallenge.business.movies.repositories.MovieRepository;
import com.backbasedevchallenge.business.ratings.dto.RatingRequestDto;
import com.backbasedevchallenge.business.ratings.dto.RatingResponseDto;
import com.backbasedevchallenge.business.ratings.entities.Rating;
import com.backbasedevchallenge.business.ratings.repositories.RatingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatingService {

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    @Transactional
    public RatingResponseDto addMovieRatting(RatingRequestDto movieRatingRequest) {
        final var movie = movieRepository.findById(movieRatingRequest.movieId())
                .orElseThrow(() -> new IllegalStateException("Movie was not found !!"));
        log.debug("Gating a movie: '{}'", movie);

        final var rating = Rating.builder()
                .id(movieRatingRequest.movieId())
                .rating(movieRatingRequest.rating().getRating())
                .build();

        movie.addMovieRating(rating);
        log.debug("Saving the following rating: '{}'", rating);

        return RatingResponseDto.builder()
                .movieTitle(movie.getNominee())
                .ratedBy(movieRatingRequest.rating().getRating())
                .build();
    }

    @Transactional
    public RatingResponseDto updateMovieRatting(RatingRequestDto movieRatingRequest) {
        final var movie = movieRepository.findById(movieRatingRequest.movieId())
                .orElseThrow(() -> new IllegalStateException("Movie was not found !!"));
        log.debug("Gating a movie: '{}'", movie);

        final var existingRating = ratingRepository.findById(movieRatingRequest.movieId())
                .orElse(null);
        log.debug("Gating an existing rating: '{}'", movie);

        var existingRatingValue = 0;

        if (ObjectUtils.isNotEmpty(existingRating)) {
            existingRatingValue = existingRating.getRating();
            existingRating.setRating(existingRatingValue + movieRatingRequest.rating().getRating());
            log.debug("Saving the following rating: '{}'", existingRatingValue + movieRatingRequest.rating().getRating());
        }

        return RatingResponseDto.builder()
                .movieTitle(movie.getNominee())
                .ratedBy(movieRatingRequest.rating().getRating())
                .build();
    }
}
