package com.backbasedevchallenge.business.movies.repositories;

import com.backbasedevchallenge.business.movies.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> getByNomineeAndCategory(String movieTitle, String category);
    @Query("select count (*) from Movie m")
    Long getNumberOfRows();
}
