package com.moviedbv2.moviedbv2;

import java.util.List;

public interface ActorServiceFace {

    List<Actor> getActors();
    Actor createActor(Actor actor);
    Actor updateActor(Actor actor);
    void deleteActor(int id);
    Actor findActor(int id);
    List<Actor> searchActor(String name);
}
