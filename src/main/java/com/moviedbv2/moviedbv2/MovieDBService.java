package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieDBService implements MovieDBServiceFace {

    @Autowired
    MovieDBRepo movieDBRepo;

    @Override
    public List<Movie> fetchAll() {
        List<Movie> movies = movieDBRepo.getMovies();

        return movies;
    }
}
