package com.backbasedevchallenge.business.ratings.entities;

import com.backbasedevchallenge.business.movies.entities.Movie;
import com.backbasedevchallenge.business.ratings.enums.MovieRatings;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "movie_rating")
public class Rating {

    @Id
    private Long id;

    private int rating;

    @MapsId
    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
