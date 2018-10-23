package com.moviedbv2.moviedbv2;

import java.util.Objects;

public class Movie {

    private int movieId, movieDuration, movieYear;
    private String movieTitle, movieGenre, moviePosterLink, movieTrailerLink;

    public Movie() {
    }

    public Movie(int movieId, int movieDuration, int movieYear, String movieTitle, String movieGenre, String moviePosterLink, String movieTrailerLink) {
        this.movieId = movieId;
        this.movieDuration = movieDuration;
        this.movieYear = movieYear;
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
        this.moviePosterLink = moviePosterLink;
        this.movieTrailerLink = movieTrailerLink;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId){
        this.movieId = movieId;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMoviePosterLink() {
        return moviePosterLink;
    }

    public void setMoviePosterLink(String moviePosterLink) {
        this.moviePosterLink = moviePosterLink;
    }

    public String getMovieTrailerLink() {
        return movieTrailerLink;
    }

    public void setMovieTrailerLink(String movieTrailerLink) {
        this.movieTrailerLink = movieTrailerLink;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieDuration=" + movieDuration +
                ", movieYear=" + movieYear +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieGenre='" + movieGenre + '\'' +
                ", moviePosterLink='" + moviePosterLink + '\'' +
                ", movieTrailerLink='" + movieTrailerLink + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return movieId == movie.movieId &&
                movieDuration == movie.movieDuration &&
                movieYear == movie.movieYear &&
                Objects.equals(movieTitle, movie.movieTitle) &&
                Objects.equals(movieGenre, movie.movieGenre) &&
                Objects.equals(moviePosterLink, movie.moviePosterLink) &&
                Objects.equals(movieTrailerLink, movie.movieTrailerLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieDuration, movieYear, movieTitle, movieGenre, moviePosterLink, movieTrailerLink);
    }
}
