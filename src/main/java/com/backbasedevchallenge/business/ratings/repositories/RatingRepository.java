package com.backbasedevchallenge.business.ratings.repositories;

import com.backbasedevchallenge.business.ratings.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query("select r from Rating r order by r.rating desc limit 10")
    List<Rating> getTopTenRatings();
}
