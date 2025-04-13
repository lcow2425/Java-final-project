package com.example.movie_catalogue.dto;

public class MovieResponse {
    private MovieDTO[] results;

    public MovieDTO[] getResults() { return results; }
    public void setResults(MovieDTO[] results) { this.results = results; }
}