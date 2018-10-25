package com.moviedbv2.moviedbv2;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;
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

    //Actors
    List<Actor> getActors();
    Actor createActor(Actor actor);
    /*Actor updateActor(Actor actor);
    void deleteActor(int id);
    Actor findActor(int id);
    List<Actor> searchActor(String name);*/

    //Movie actor relation
    List<Actor> getRelatedMovieActor(int movieId);
    List<Actor> getUnrelatedMovieActor(int movieId);
    void createRelation(int actorId, int movieId);
    void removeRelation(int actorId, int movieId);
}
