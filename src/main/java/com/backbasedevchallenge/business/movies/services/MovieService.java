package com.backbasedevchallenge.business.movies.services;

import com.backbasedevchallenge.business.movies.dto.MovieResponseDto;
import com.backbasedevchallenge.business.movies.entities.Movie;
import com.backbasedevchallenge.business.movies.enums.OscarWinnerStatuses;
import com.backbasedevchallenge.business.movies.repositories.MovieRepository;
import com.backbasedevchallenge.business.ratings.repositories.RatingRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.logging.log4j.util.Strings.EMPTY;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    private static final String BASE_URL = "http://www.omdbapi.com/";
    private static final String TOKEN_KEY = "apikey";
    private static final String TOKEN_VALUE = "ebe2fcbc";
    private static final String OSCAR_CATEGORY = "Best Picture";
    private static final String TITLE_QUERY_KEY = "t";
    private static final String FORMAT_QUERY_KEY = "r";
    private static final String FORMAT_QUERY_VALUE = "r";
    private static final String TYPE_QUERY_KEY = "type";
    private static final String TYPE_QUERY_VALUE = "movie";
    private static final String REPLACE_TARGET_DOLLAR = "$";
    private static final String REPLACE_TARGET_COMA = ",";

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = new RequestSpecBuilder().build()
                .baseUri(BASE_URL)
                .queryParams(Map.of(
                        FORMAT_QUERY_KEY, FORMAT_QUERY_VALUE,
                        TYPE_QUERY_KEY, TYPE_QUERY_VALUE,
                        TOKEN_KEY, TOKEN_VALUE));
    }

    public String isMovieBestPictureOscarWinner(String movieTitle) {
        final var movies = movieRepository.getByNomineeAndCategory(movieTitle, OSCAR_CATEGORY);
        log.debug("Gating the following movies by title and category: '{}'", movies);

        final var years = movies.stream()
                .filter(movie -> OscarWinnerStatuses.YES.toString().equals(movie.getWon()))
                .map(Movie::getYear)
                .toList();

        return years.isEmpty()
                ? "The movie '%s' has never won Oscar".formatted(movieTitle)
                : "The movie has been winner in the: %s".formatted(years);

    }

    @Cacheable(cacheNames = "topTenOrderedByBoxOffice")
    public List<MovieResponseDto> getTopTenOrderedByBoxOffice() {
        var topTenRatings = ratingRepository.getTopTenRatings();
        log.debug("Gating top ten ordered by boxOffice: '{}'", topTenRatings);

        return topTenRatings.stream()
                .map(rating -> getMovieByTitle(rating.getMovie().getNominee()))
                .sorted(Comparator.comparingLong(movieResponseDto -> Long.parseLong(movieResponseDto.boxOffice()
                        .replace(REPLACE_TARGET_DOLLAR, EMPTY)
                        .replace(REPLACE_TARGET_COMA, EMPTY))))
                .toList();
    }

    private MovieResponseDto getMovieByTitle(String movieTitle) {
        return given()
                .when()
                .queryParam(TITLE_QUERY_KEY, movieTitle)
                .get()
                .getBody().as(MovieResponseDto.class);
    }
}
