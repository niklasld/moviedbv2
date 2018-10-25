package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ActorController {

    @Autowired
    ActorServiceFace actorServiceFace;
}
