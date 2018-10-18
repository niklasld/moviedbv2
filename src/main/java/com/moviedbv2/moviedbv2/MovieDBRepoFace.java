package com.moviedbv2.moviedbv2;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository

public interface MovieDBRepoFace {
    ArrayList<Movie> getMovies();
    //Movie createMovie(Movie movie);
    //Movie updateMovie(Movie movie);
    //void deleteMovie(int id);
    //Movie findMovie(int id);
    //List<Movie> searchMovie(String title);
}