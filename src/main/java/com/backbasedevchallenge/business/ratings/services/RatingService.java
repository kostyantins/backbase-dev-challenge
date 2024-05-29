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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatingService {

    private final MovieRepository movieRepository;

    @Transactional
    public RatingResponseDto addMovieRatting(RatingRequestDto movieRatingRequest) {
        final var movie = movieRepository.findById(movieRatingRequest.movieId())
                .orElseThrow(() -> new IllegalStateException("Movie was not found !!"));
        log.debug("Gating a movie: '{}'", movie);

        final var userEmail = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        final var rating = Rating.builder()
                .movie(movie)
                .userEmail(userEmail)
                .rating(movieRatingRequest.rating().getRating())
                .build();

        movie.addMovieRating(rating);
        log.debug("Saving the following rating: '{}'", rating);

        return RatingResponseDto.builder()
                .movieTitle(movie.getNominee())
                .ratedBy(userEmail)
                .rating(movieRatingRequest.rating().getRating())
                .build();
    }
}
