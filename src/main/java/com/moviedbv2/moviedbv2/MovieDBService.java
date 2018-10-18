package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MovieDBService implements MovieDBServiceFace {

    @Autowired
    MovieDBRepo movieDBRepo;

    @Override
    public List<Movie> fetchAll() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(0,120,1992,"Peters Rejse","Horror","http://","http://"));
        movies.add(new Movie(1,120,1992,"Peters Rejse","Horror","http://","http://"));

        //movies = movieDBRepo.getMovies();

        return movies;
    }
}
