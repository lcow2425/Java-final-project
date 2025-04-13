package com.example.movie_catalogue.service;

import com.example.movie_catalogue.dto.MovieDTO;
import com.example.movie_catalogue.dto.MovieResponse;
import com.example.movie_catalogue.model.Movie;
import com.example.movie_catalogue.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieService {
    private final RestTemplate restTemplate;
    private final MovieRepository movieRepository;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.base-url}")
    private String baseUrl;

    public MovieService(RestTemplate restTemplate, MovieRepository movieRepository) {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
    }

    public MovieDTO[] getTrendingMovies() {
        try {
            String url = baseUrl + "/trending/movie/week?api_key=" + apiKey;
            MovieResponse response = restTemplate.getForObject(url, MovieResponse.class);
            return response != null ? response.getResults() : new MovieDTO[0];
        } catch (Exception e) {
            System.err.println("Error fetching trending movies: " + e.getMessage());
            return new MovieDTO[0];
        }
    }

    public MovieDTO[] searchMovies(String query) {
        try {
            String url = baseUrl + "/search/movie?api_key=" + apiKey + "&query=" + query;
            MovieResponse response = restTemplate.getForObject(url, MovieResponse.class);
            return response != null ? response.getResults() : new MovieDTO[0];
        } catch (Exception e) {
            System.err.println("Error searching movies: " + e.getMessage());
            return new MovieDTO[0];
        }
    }

    public MovieDTO getMovieDetails(Long id) {
        try {
            String url = baseUrl + "/movie/" + id + "?api_key=" + apiKey;
            return restTemplate.getForObject(url, MovieDTO.class);
        } catch (Exception e) {
            System.err.println("Error fetching movie details: " + e.getMessage());
            return new MovieDTO();
        }
    }

    public void addFavorite(Long id) {
        MovieDTO movieDTO = getMovieDetails(id);
        if (!movieRepository.existsById(id)) {
            Movie movie = new Movie(
                movieDTO.getId(),
                movieDTO.getTitle(),
                movieDTO.getOverview(),
                movieDTO.getRelease_date(),
                movieDTO.getPoster_path(),
                movieDTO.getVote_average()
            );
            movieRepository.save(movie);
        }
    }

    public void removeFavorite(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getFavorites() {
        return movieRepository.findAll();
    }
}