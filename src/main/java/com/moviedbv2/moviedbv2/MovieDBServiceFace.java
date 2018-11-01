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
    List<User> getUsers();
    User createUser(User user);

    boolean loginMatch(User user);
    User loggedIn(User user);
    List<User> searchUser(String search);
    User findUser(int userId);
    User updateUser(User user);
    void deleteUser(int id);

    //Movie actor relations
    List<Actor> getRelatedMovieActor(int movieId);
    List<Actor> getUnrelatedMovieActor(int movieId);
    void createRelation(int actorId, int movieId);
    void removeRelation(int actorId, int movieId);

}
