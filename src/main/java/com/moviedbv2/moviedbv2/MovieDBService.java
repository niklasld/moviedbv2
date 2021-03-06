package com.moviedbv2.moviedbv2;

//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieDBService implements MovieDBServiceFace {

    @Autowired
    MovieDBRepo movieDBRepoFace;

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = movieDBRepoFace.getMovies();

        return movies;
    }

    @Override
    public Movie createMovie(Movie movie) {
        Movie movie_ = movieDBRepoFace.createMovie(movie);
        return movie_;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        movie = movieDBRepoFace.updateMovie(movie);
        return movie;
    }

    @Override
    public void deleteMovie(int id) {
        movieDBRepoFace.deleteMovie(id);
    }

    @Override
    public Movie findMovie(int id) {
        Movie movie = movieDBRepoFace.findMovie(id);
        return movie;
    }

    @Override
    public List<Movie> searchMovie(String search) {
        List<Movie> movies = movieDBRepoFace.searchMovie(search);
        return movies;
    }






    @Override
    public List<User> getUsers() {
        List<User> users = movieDBRepoFace.getUsers();

        return users;
    }

    @Override
    public User createUser(User user) {
        user = movieDBRepoFace.createUser(user);
        return user;
    }





    @Override
    public boolean loginMatch(User user) {
        boolean loginMatch;

        User loginUser = movieDBRepoFace.findLogin(user.getUserName(),user.getUserPassword());

        if(loginUser.getUserName() != null && loginUser.userPassword != null) {
            loginMatch = true;
        }
        else{
            loginMatch = false;
        }

        return loginMatch;
    }

    @Override
    public User loggedIn(User user) {

        user = movieDBRepoFace.findLogin(user.getUserName(),user.getUserPassword());
        return user;
    }

    @Override
    public List<User> searchUser(String search) {
        List<User> users = movieDBRepoFace.searchUser(search);
        return users;
    }

    @Override
    public User findUser(int userId) {
        User foundUser = new User();

        foundUser = movieDBRepoFace.findUser(userId);

        return foundUser;
    }

    @Override
    public User updateUser(User user) {
        user = movieDBRepoFace.updateUser(user);

        return user;
    }

    @Override
    public void deleteUser(int id) {
        movieDBRepoFace.deleteUser(id);
    }

    @Override
    public List<Actor> getRelatedMovieActor(int movieId) {
        List<Actor> actors = movieDBRepoFace.getRelatedMovieActor(movieId);
        return actors;
    }

    @Override
    public List<Actor> getUnrelatedMovieActor(int movieId) {
        List<Actor> actors = movieDBRepoFace.getUnrelatedMovieActor(movieId);
        return actors;
    }

    @Override
    public void createRelation(int actorId, int movieId) {
        movieDBRepoFace.createRelation(actorId, movieId);
    }

    @Override
    public void removeRelation(int actorId, int movieId) {
        movieDBRepoFace.removeRelation(actorId, movieId);
    }
}
