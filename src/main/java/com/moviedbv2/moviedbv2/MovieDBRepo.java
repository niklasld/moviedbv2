package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class MovieDBRepo implements MovieDBRepoFace {
    @Override
    public List<Movie> getMovies() {
        String sql = "SELECT * FROM movies";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Movie>>() {
            @Override
            public List<Movie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int movieId, movieYear, movieDuration;
                String movieTitle, movieTrailerLink, moviePosterLink, movieGenre;
                ArrayList<Movie> movies = new ArrayList<>();

                while (rs.next()) {
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


    @Autowired
    JdbcTemplate template;

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

        log.info("create movie" + movieTitle + releaseYear + genre + duration + trailerLink + posterLink);
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
    public List<Actor> getActors() {
        String sql = "SELECT * FROM actors";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Actor>>() {
            @Override
            public List<Actor> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int actorId;
                String firstName, lastName;
                ArrayList<Actor> actors = new ArrayList<>();

                while (rs.next()) {
                    actorId = rs.getInt("actorId");
                    firstName = rs.getString("firstName");
                    lastName = rs.getString("lastName");

                    actors.add(new Actor(actorId, firstName, lastName));
                }
                return actors;
            }
        });

    }

    @Override
    public Actor createActor(Actor actor) {
        Logger log = Logger.getLogger(MovieDBService.class.getName());

        String sql = "INSERT INTO actors VALUE(default, ?, ?)";
        String firstName = actor.getFirstName();
        String lastName = actor.getLastName();

        log.info("create actor" + firstName + lastName);
        this.template.update(sql, firstName, lastName);

        return actor;
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
    public List<Movie> searchMovie(String search) {
        String sql = "SELECT * FROM movies WHERE movieTitle LIKE ? OR movieGenre LIKE ?";

        search = "%" + search + "%";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Movie>>() {
            @Override
            public List<Movie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int movieId, movieYear, movieDuration;
                String movieTitle, movieTrailerLink, moviePosterLink, movieGenre;
                ArrayList<Movie> movies = new ArrayList<>();

                while (rs.next()) {
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
        }, search, search);

    }

    @Override
    public List<Actor> getRelatedMovieActor(int movieId) {
        String sql = "SELECT actors.actorId, firstName, lastName FROM actors\n" +
                "INNER JOIN movieActorRelation ON actors.actorId = movieActorRelation.fk_actorId\n" +
                "WHERE fk_movieId = ?";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Actor>>() {
            @Override
            public List<Actor> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int actorId;
                String firstName, lastName;
                List<Actor> actors = new ArrayList<>();

                while (rs.next()) {
                    actorId = rs.getInt("actorId");
                    firstName = rs.getString("firstName");
                    lastName = rs.getString("lastName");

                    actors.add(new Actor(actorId, firstName, lastName));
                }
                return actors;
            }
        }, movieId);
    }

    @Override
    public List<Actor> getUnrelatedMovieActor(int movieId) {
        String sql = "SELECT actorId, firstName, lastName FROM actors\n" +
                "CROSS JOIN movies\n" +
                "WHERE (actorId, movieId) NOT IN (\n" +
                "    SELECT fk_actorId, fk_movieId\n" +
                "    FROM movieActorRelation\n" +
                "    )\n" +
                "AND movieId = ?";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Actor>>() {
            @Override
            public List<Actor> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int actorId;
                String firstName, lastName;
                List<Actor> actors = new ArrayList<>();

                while (rs.next()) {
                    actorId = rs.getInt("actorId");
                    firstName = rs.getString("firstName");
                    lastName = rs.getString("lastName");

                    actors.add(new Actor(actorId, firstName, lastName));
                }
                return actors;
            }
        }, movieId);
    }

    @Override
    public void createRelation(int actorId, int movieId) {
        String sql = "INSERT INTO movieActorRelation VALUES (DEFAULT, ?, ?)";
        this.template.update(sql, movieId, actorId);
    }

    @Override
    public void removeRelation(int actorId, int movieId) {
        String sql = "DELETE FROM movieActorRelation WHERE fk_movieId = ? AND fk_actorId = ?";
        this.template.update(sql, movieId, actorId);

    }
}
