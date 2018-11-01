package com.moviedbv2.moviedbv2;

import java.util.List;

public interface MovieDBServiceFace {
    //Movies
    List<Movie> getMovies();
    Movie createMovie(Movie movie);
    Movie updateMovie(Movie movie);
    void deleteMovie(int id);
    Movie findMovie(int id);
    List<Movie> searchMovie(String search);

    //Users
    boolean loginMatch(User user);
    List<User> getUsers();
    User loggedIn(User user);
    List<User> searchUser(String search);

    //Movie actor relations
    List<Actor> getRelatedMovieActor(int movieId);
    List<Actor> getUnrelatedMovieActor(int movieId);
    void createRelation(int actorId, int movieId);
    void removeRelation(int actorId, int movieId);
}
