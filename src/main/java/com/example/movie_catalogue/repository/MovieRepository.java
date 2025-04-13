package com.example.movie_catalogue.repository;

import com.example.movie_catalogue.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}