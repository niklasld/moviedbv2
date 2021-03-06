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
    List<User> getUsers();
    User findLogin(String userName, String userPassword);
    List<User> searchUser(String search);
    User createUser(User user);
    User findUser(int userId);
    User updateUser(User user);
    void deleteUser(int id);

    //Movie actor relation
    List<Actor> getRelatedMovieActor(int movieId);
    List<Actor> getUnrelatedMovieActor(int movieId);
    void createRelation(int actorId, int movieId);
    void removeRelation(int actorId, int movieId);
}
