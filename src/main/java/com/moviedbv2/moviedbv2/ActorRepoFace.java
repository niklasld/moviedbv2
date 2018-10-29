package com.moviedbv2.moviedbv2;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepoFace {
    List<Actor> getActors();
    Actor createActor(Actor actor);
    Actor updateActor(Actor actor);
    void deleteActor(int id);
    Actor findActor(int id);
    List<Actor> searchActor(String search);
}
