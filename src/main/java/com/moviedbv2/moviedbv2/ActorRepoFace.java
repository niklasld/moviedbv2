package com.moviedbv2.moviedbv2;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepoFace {
    List<Actor> getActors();
    Actor createActor(Actor actor);
    void addActorMovieRelation(int movieId, int actorId);
    Movie updateActor(Actor actor);
    void deleteActor(int id);
    Movie findActor(int id);
    List<Movie> searchActor(String actor);
    List<Actor> actorsNotRelatedToMovie(int movieId);
}
