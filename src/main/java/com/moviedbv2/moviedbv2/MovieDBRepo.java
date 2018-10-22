package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class MovieDBRepo implements MovieDBRepoFace {

    @Autowired
    JdbcTemplate template;


    /*public ArrayList<Movie> getMovies() {
        String sql = "SELECT * FROM movies";

        return this.template.query(sql, new ResultSetExtractor<ArrayList<Movie>>() {

            @Override
            public ArrayList<Movie> extractData(ResultSet rs) throws SQLException, DataAccessException {

                ArrayList<Movie> movie = new ArrayList<>();
                int idMovies,  releaseYear, duration;
                String title, genre, trailerLink, pictureLink;

                while(rs.next()) {
                    idMovies = rs.getInt("idmovies");
                    title = rs.getString("title");
                    releaseYear = rs.getInt("releaseyear");
                    genre = rs.getString("genre");
                    duration = rs.getInt("duration");
                    trailerLink = rs.getString("trailerLink");
                    pictureLink = rs.getString("pictureLink");

                    movie.add(new Movie(idMovies,duration,releaseYear,title,genre,pictureLink,trailerLink));
                }

                return movie;
            }

        });
    }*/

    @Override
    public List<Movie> getMovies() {
        String sql = "SELECT * FROM moviedb.movies";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Movie>>(){
            @Override
            public List<Movie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int movieId, movieYear, movieDuration;
                String movieTitle, movieTrailerLink, moviePictureLink, movieGenre;
                ArrayList<Movie> movies = new ArrayList<>();

                while(rs.next()){
                    movieId = rs.getInt("idmovies");
                    movieTitle = rs.getString("title");
                    movieYear = rs.getInt("releaseYear");
                    movieTrailerLink = rs.getString("trailerlink");
                    movieDuration = rs.getInt("duration");
                    moviePictureLink = rs.getString("pictureLink");
                    movieGenre = rs.getString("genre");

                    movies.add(new Movie(movieId, movieDuration, movieYear, movieTitle, movieGenre, moviePictureLink, movieTrailerLink));
                }
                return movies;
            }
        });

    }

    @Override
    public Movie createMovie(Movie movie) {
        Logger log = Logger.getLogger(MovieDBService.class.getName());

        String sql = "INSERT INTO moviedb.movies VALUE(default, ?, ?, ?, ?, ?, ?)";
        String movieTitle = movie.getMovieTitle();
        int releaseYear = movie.getMovieYear();
        String genre = movie.getMovieGenre();
        int duration = movie.getMovieDuration();
        String trailerLink = movie.getMovieTrailerLink();
        String pictureLink = movie.getMoviePosterLink();

        log.info("create movie"+movieTitle+releaseYear+genre+duration+trailerLink+pictureLink);
        //this.template.update(sql, movie);
        this.template.update(sql, movieTitle, releaseYear, genre, duration, trailerLink, pictureLink);

        return movie;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return null;
    }

    @Override
    public void deleteMovie(int id) {
        String sql = "DELETE from movies WHERE idmovies = ?";
        this.template.update(sql,id);

    }

    @Override
    public Movie findMovie(int id) {
        return null;
    }

    @Override
    public List<Movie> searchMovie(String title) {
        String sql = "SELECT * FROM moviedb.movies WHERE title LIKE ?";

        title = "%" + title + "%";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Movie>>(){
            @Override
            public List<Movie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int movieId, movieYear, movieDuration;
                String movieTitle, movieTrailerLink, moviePictureLink, movieGenre;
                ArrayList<Movie> movies = new ArrayList<>();

                while(rs.next()){
                    movieId = rs.getInt("idmovies");
                    movieTitle = rs.getString("title");
                    movieYear = rs.getInt("releaseyear");
                    movieTrailerLink = rs.getString("trailerlink");
                    movieDuration = rs.getInt("duration");
                    moviePictureLink = rs.getString("pictureLink");
                    movieGenre = rs.getString("genre");

                    movies.add(new Movie(movieId, movieDuration, movieYear, movieTitle, movieGenre, moviePictureLink, movieTrailerLink));
                }
                return movies;
            }
        }, title);

    }

}
