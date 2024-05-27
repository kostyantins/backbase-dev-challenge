package com.backbasedevchallenge.unit.movie;

import com.backbasedevchallenge.business.movies.entities.Movie;
import com.backbasedevchallenge.business.movies.enums.OscarWinnerStatuses;
import com.backbasedevchallenge.business.movies.repositories.MovieRepository;
import com.backbasedevchallenge.business.movies.services.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieUnitTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void shouldGetBestPictureOscarWinnerMovie() {
        final var title = "The King's Speech";

        final var movie = Movie.builder()
                .id(1L)
                .year("2010")
                .additionalInfo("Additional info")
                .nominee(title)
                .won(OscarWinnerStatuses.YES.toString())
                .build();

        when(movieRepository.getByNomineeAndCategory(any(), any())).thenReturn(List.of(movie));

        final var actualMovie = movieService.isMovieBestPictureOscarWinner(title);

        assertThat(actualMovie).isEqualTo("The movie has been winner in the: %s".formatted("[2010]"));
    }
}
