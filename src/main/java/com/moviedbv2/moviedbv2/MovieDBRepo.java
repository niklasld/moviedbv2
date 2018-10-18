package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class MovieDBRepo implements MovieDBRepoFace {

    @Autowired
    JdbcTemplate template;

    public ArrayList<Movie> getMovies() {
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
    }
}
