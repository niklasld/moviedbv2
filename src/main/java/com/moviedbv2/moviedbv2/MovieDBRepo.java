package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository


public class MovieDBRepo {
    @Autowired
    JdbcTemplate template;

    public void updateMovie(Movie movie) {


    }

    public void getMovie(Movie movie){


    }
}
