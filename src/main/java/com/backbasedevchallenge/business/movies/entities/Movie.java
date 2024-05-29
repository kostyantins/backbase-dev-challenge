package com.backbasedevchallenge.business.movies.entities;

import com.backbasedevchallenge.business.ratings.entities.Rating;
import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByPosition(position = 0)
    private String year;

    @CsvBindByPosition(position = 1)
    private String category;

    @CsvBindByPosition(position = 2)
    private String nominee;

    @CsvBindByPosition(position = 3)
    @Column(name = "additional_info")
    private String additionalInfo;

    @CsvBindByPosition(position = 4)
    private String won;

    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Rating> movieRatings = new ArrayList<>();

    public void addMovieRating(Rating movieRating){
        if (ObjectUtils.isEmpty(movieRating)) {
            throw new IllegalArgumentException("Movie rating should not be null or empty !!");
        }

        movieRating.setMovie(this);
        movieRatings.add(movieRating);
    }
}
