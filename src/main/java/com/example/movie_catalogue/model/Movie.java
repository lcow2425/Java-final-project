package com.example.movie_catalogue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @Id
    private Long id;
    private String title;
    private String overview;
    private String releaseDate;
    private String poster_path;
    private Double voteAverage;

    // Constructors
    public Movie() {}

    public Movie(Long id, String title, String overview, String releaseDate, String poster_path, Double voteAverage) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.poster_path = poster_path;
        this.voteAverage = voteAverage;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }
    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public String getPosterPath() { return poster_path; }
    public void setPosterPath(String posterPath) { this.poster_path = posterPath; }
    public Double getVoteAverage() { return voteAverage; }
    public void setVoteAverage(Double voteAverage) { this.voteAverage = voteAverage; }
}