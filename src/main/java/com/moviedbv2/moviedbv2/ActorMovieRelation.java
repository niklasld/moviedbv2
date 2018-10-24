package com.moviedbv2.moviedbv2;

public class ActorMovieRelation {
    int idActorMovieRelations, movieId, actorId;

    public ActorMovieRelation() {
    }

    public ActorMovieRelation(int movieId, int actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public ActorMovieRelation(int idActorMovieRelations, int movieId, int actorId) {
        this.idActorMovieRelations = idActorMovieRelations;
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public int getIdActorMovieRelations() {
        return idActorMovieRelations;
    }

    public void setIdActorMovieRelations(int idActorMovieRelations) {
        this.idActorMovieRelations = idActorMovieRelations;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Override
    public String toString() {
        return "ActorMovieRelation{" +
                "idActorMovieRelations=" + idActorMovieRelations +
                ", movieId=" + movieId +
                ", actorId=" + actorId +
                '}';
    }
}
