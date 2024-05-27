package com.backbasedevchallenge.business.movies.services;

import com.backbasedevchallenge.business.movies.entities.Movie;
import com.backbasedevchallenge.business.movies.repositories.MovieRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieCsvService {

    private final MovieRepository repository;

    public void uploadCsv() throws IOException {
        final var file = new File("src/main/resources/files/academy_awards.csv");
        final var absolutePath = file.getAbsolutePath();
        log.info("Preparing to read the following file: '{}'", absolutePath);

        final var movies = new CsvToBeanBuilder(new FileReader(absolutePath))
                .withSkipLines(1)
                .withType(Movie.class)
                .build()
                .parse();

        movies.remove(0);

        log.info("Starting saving csv data...");
        repository.saveAll(movies);
        log.info("Finished saving csv data");
    }

    @PostConstruct
    public void triggerCsvUpload() throws IOException {
        uploadCsv();
    }

}
