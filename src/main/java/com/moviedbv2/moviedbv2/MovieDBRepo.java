package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class MovieDBRepo implements MovieDBRepoFace {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Movie> getMovies() {
        String sql = "SELECT * FROM movies";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Movie>>(){
            @Override
            public List<Movie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int movieId, movieYear, movieDuration;
                String movieTitle, movieTrailerLink, moviePosterLink, movieGenre;
                ArrayList<Movie> movies = new ArrayList<>();

                while(rs.next()){
                    movieId = rs.getInt("movieId");
                    movieTitle = rs.getString("movieTitle");
                    movieYear = rs.getInt("movieYear");
                    movieTrailerLink = rs.getString("movieTrailerlink");
                    movieDuration = rs.getInt("movieDuration");
                    moviePosterLink = rs.getString("moviePosterLink");
                    movieGenre = rs.getString("movieGenre");

                    movies.add(new Movie(movieId, movieDuration, movieYear, movieTitle, movieGenre, moviePosterLink, movieTrailerLink));
                }
                return movies;
            }
        });

    }

    @Override
    public Movie createMovie(Movie movie) {
        Logger log = Logger.getLogger(MovieDBService.class.getName());

        String sql = "INSERT INTO movies VALUE(default, ?, ?, ?, ?, ?, ?)";
        String movieTitle = movie.getMovieTitle();
        int releaseYear = movie.getMovieYear();
        String genre = movie.getMovieGenre();
        int duration = movie.getMovieDuration();
        String trailerLink = movie.getMovieTrailerLink();
        String posterLink = movie.getMoviePosterLink();

        log.info("create movie"+movieTitle+releaseYear+genre+duration+trailerLink+posterLink);
        this.template.update(sql, movieTitle, releaseYear, genre, duration, trailerLink, posterLink);

        return movie;
    }

    @Override
    public Movie updateMovie(Movie movie) {

        String sql = "UPDATE movies SET movieTitle = ?, movieYear = ?, movieGenre = ?, movieDuration = ?, movieTrailerLink = ?, moviePosterLink = ? WHERE movieId = ?";
        String movieTitle = movie.getMovieTitle();
        String movieGenre = movie.getMovieGenre();
        String movieTrailerLink = movie.getMovieTrailerLink();
        String moviePosterLink = movie.getMoviePosterLink();
        int movieId = movie.getMovieId();
        int movieYear = movie.getMovieYear();
        int movieDuration = movie.getMovieDuration();
        this.template.update(sql, movieTitle, movieYear, movieGenre, movieDuration, movieTrailerLink, moviePosterLink, movieId);
        return movie;
   }

    @Override
    public void deleteMovie(int id) {
        String sql = "DELETE FROM movies WHERE movieId=?";
        this.template.update(sql, id);
    }


    @Override
    public Movie findMovie(int id) {
        String sql = "SELECT * FROM movies WHERE movieId = ?";

        RowMapper<Movie> rowMapper = new BeanPropertyRowMapper<>(Movie.class);

        Movie movie = template.queryForObject(sql, rowMapper, id);

        return movie;


    }


    @Override
    public List<Movie> searchMovie(String title) {
        String sql = "SELECT * FROM movies WHERE movieTitle LIKE ?";

        title = "%" + title + "%";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Movie>>(){
            @Override
            public List<Movie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int movieId, movieYear, movieDuration;
                String movieTitle, movieTrailerLink, moviePosterLink, movieGenre;
                ArrayList<Movie> movies = new ArrayList<>();

                while(rs.next()){
                    movieId = rs.getInt("movieId");
                    movieTitle = rs.getString("movieTitle");
                    movieYear = rs.getInt("movieYear");
                    movieTrailerLink = rs.getString("movieTrailerlink");
                    movieDuration = rs.getInt("movieDuration");
                    moviePosterLink = rs.getString("moviePosterLink");
                    movieGenre = rs.getString("movieGenre");

                    movies.add(new Movie(movieId, movieDuration, movieYear, movieTitle, movieGenre, moviePosterLink, movieTrailerLink));
                }
                return movies;
            }
        }, title);

    }

}
