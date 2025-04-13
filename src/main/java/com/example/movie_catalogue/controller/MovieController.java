package com.example.movie_catalogue.controller;

import com.example.movie_catalogue.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movies", movieService.getTrendingMovies());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("movies", movieService.searchMovies(query));
        model.addAttribute("query", query);
        return "index";
    }

    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {
        model.addAttribute("movie", movieService.getMovieDetails(id));
        return "movie-details";
    }

    @PostMapping("/favorite/{id}")
    public String addFavorite(@PathVariable Long id) {
        movieService.addFavorite(id);
        return "redirect:/"; // Redirect back to home page
    }

    @PostMapping("/unfavorite/{id}")
    public String removeFavorite(@PathVariable Long id) {
        movieService.removeFavorite(id);
        return "redirect:/favorites"; // Redirect back to favorites page
    }

    @GetMapping("/favorites")
    public String favorites(Model model) {
        model.addAttribute("favorites", movieService.getFavorites());
        return "favorites";
    }
}