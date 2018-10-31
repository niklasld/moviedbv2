package com.moviedbv2.moviedbv2;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MovieDBRepoFace {
    //Movies
    List<Movie> getMovies();
    Movie createMovie(Movie movie);
    Movie updateMovie(Movie movie);
    void deleteMovie(int id);
    Movie findMovie(int id);
    List<Movie> searchMovie(String search);

    //Users
    User findLogin(String userName, String userPassword);

    //Movie actor relation
    List<Actor> getRelatedMovieActor(int movieId);
    List<Actor> getUnrelatedMovieActor(int movieId);
    void createRelation(int actorId, int movieId);
    void removeRelation(int actorId, int movieId);
}
