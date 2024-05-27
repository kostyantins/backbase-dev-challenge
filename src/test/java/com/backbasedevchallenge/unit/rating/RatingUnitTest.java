package com.backbasedevchallenge.unit.rating;

import com.backbasedevchallenge.business.movies.entities.Movie;
import com.backbasedevchallenge.business.movies.enums.OscarWinnerStatuses;
import com.backbasedevchallenge.business.movies.repositories.MovieRepository;
import com.backbasedevchallenge.business.ratings.dto.RatingRequestDto;
import com.backbasedevchallenge.business.ratings.enums.MovieRatings;
import com.backbasedevchallenge.business.ratings.repositories.RatingRepository;
import com.backbasedevchallenge.business.ratings.services.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingUnitTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private RatingService ratingService;

    @Test
    void shouldAddMovieRating() {
        final var movie = Movie.builder()
                .id(1L)
                .year("2010")
                .additionalInfo("Additional info")
                .nominee("The King's Speech")
                .won(OscarWinnerStatuses.YES.toString())
                .build();

        final var ratingRequest = RatingRequestDto.builder()
                .movieId(movie.getId())
                .rating(MovieRatings.PERFECT)
                .build();

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));

        final var rating = ratingService.addMovieRatting(ratingRequest);

        assertThat(rating.ratedBy()).isEqualTo(5);
    }
}
