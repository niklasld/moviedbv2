package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActorService implements ActorServiceFace {

    @Autowired
    ActorRepo actorRepo;

    @Override
    public List<Actor> fetchAll() {
        List<Actor> actors = actorRepo.getActors();

        return actors;
    }
}
