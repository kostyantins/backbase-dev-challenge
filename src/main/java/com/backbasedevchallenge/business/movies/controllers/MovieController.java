package com.backbasedevchallenge.business.movies.controllers;

import com.backbasedevchallenge.business.movies.dto.MovieOscarWinnerResultResponseDto;
import com.backbasedevchallenge.business.movies.dto.MovieResponseDto;
import com.backbasedevchallenge.business.movies.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/oscar/best/pictures")
    public ResponseEntity<MovieOscarWinnerResultResponseDto> isMovieBestPictureOscarWinner(@RequestParam String movieTitle) {
        final var movieOscarWinResult = movieService.isMovieBestPictureOscarWinner(movieTitle);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MovieOscarWinnerResultResponseDto.builder()
                        .movieOscarWinner(movieOscarWinResult)
                        .build());
    }

    @GetMapping("/top/ten/rating")
    public ResponseEntity<List<MovieResponseDto>> getTopTenOrderedByBoxOffice() {
        final var movieOscarWinnerResult = movieService.getTopTenOrderedByBoxOffice();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movieOscarWinnerResult);
    }
}
