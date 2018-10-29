package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService implements ActorServiceFace {

    @Autowired
    ActorRepoFace actorRepoFace;

    @Override
    public List<Actor> getActors() {
        List<Actor> actors = actorRepoFace.getActors();
        return actors;
    }

    @Override
    public Actor createActor(Actor actor) {
        Actor actor_ = actorRepoFace.createActor(actor);
        return actor_;
    }

    @Override
    public Actor updateActor(Actor actor) {
        return null;
    }

    @Override
    public void deleteActor(int id) {actorRepoFace.deleteActor(id);}

    @Override
    public Actor findActor(int id){
    Actor actor = actorRepoFace.findActor(id);
        return actor;
    }

    @Override
    public List<Actor> searchActor(String name) {
        return null;
    }
}
