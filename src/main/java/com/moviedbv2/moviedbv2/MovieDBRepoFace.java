package com.moviedbv2.moviedbv2;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MovieDBRepoFace {
    List<Movie> getMovies();
    Movie createMovie(Movie movie);
    Movie updateMovie(Movie movie);

    Actor createActor(Actor actor);

    void deleteMovie(int id);
    Movie findMovie(int id);
    List<Movie> searchMovie(String title);
}
