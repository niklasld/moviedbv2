package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class ActorController {

    @Autowired
    ActorServiceFace actorServiceFace;

    private final String ACTORS= "actors";
    private final String CREATEACTOR = "createactor";
    private final String EDITACTOR = "editactor";
    private final String REDIRECT = "redirect:/";
    private final String DELETEACTOR = "deleteactor";
    private final String ADDACTORTOMOVIE = "addactortomovie";
    private final String ADDACTORTOMOVIECONFIRM = "addactortomovieconfirm";

    public ActorController() {

    }

    Logger log = Logger.getLogger(ActorController.class.getName());

    @GetMapping("/actors")
    public String actors(Model model) {
        log.info("actors called...");

        List<Actor> actors = actorServiceFace.getActors();
        model.addAttribute("actors", actors);
        model.addAttribute("isActors", true);

        model.addAttribute("pageTitle", "Actors");

        return ACTORS;
    }

    @PostMapping("/createActor")
    public String createActor(@ModelAttribute Actor actor, Model model) {
        log.info("Create actor called...");

        actorServiceFace.createActor(actor);

        model.addAttribute("actor",actorServiceFace.getActors());
        model.addAttribute("pageTitle", "Create actor");

        return REDIRECT+ACTORS;
    }

    @GetMapping("/createActor")
    public String createActor (Model model){
        log.info("createActor getmapping called...");

        model.addAttribute("actor", new Actor());
        model.addAttribute("pageTitle", "Create actor");

        return CREATEACTOR;
    }

    @GetMapping("/editActor/{id}")
    public String editActor(@PathVariable("id") int id, Model model) {
        log.info("Edit actor Called. id="+id);
        Actor actor = actorServiceFace.findActor(id);
        log.info("actorId = "+actor.getActorId());
        model.addAttribute("actor", actor);
        model.addAttribute("pageTitle","Edit Actor");

        return EDITACTOR;

    }

    @PutMapping("/editActor")
    public String editActor(@ModelAttribute Actor actor, Model model) {
        actorServiceFace.updateActor(actor);
        log.info("Edit actor put mapping called (actor updated in sql) id="+actor.getActorId()+" firstname = "+actor.getFirstName());
        model.addAttribute("",actorServiceFace.getActors());
        model.addAttribute("pageTitle","Edit Actor");

        return REDIRECT+ACTORS;
    }


    @GetMapping("/deleteActor/{id}")
    public String deleteActor(@PathVariable Integer id, Model model) {
        log.info("Delete Actor with id: "+id+"?");

        model.addAttribute("actor", actorServiceFace.findActor(id));
        String firstName = actorServiceFace.findActor(id).getFirstName();
        model.addAttribute("pageTitle", "Delete actor (" + firstName + ")");

        return DELETEACTOR;
    }

    @PutMapping("/deleteActor")
    public String delete(@ModelAttribute Actor actor, Model model) {
        log.info("delete confirmed deleting actor "+actor.getActorId());
        int id = actor.getActorId();
        String first = actor.getFirstName();
        log.info("id is "+id+ " first: "+actor.getLastName());

        actorServiceFace.deleteActor(id);

        model.addAttribute("actors", actorServiceFace.getActors());
        model.addAttribute("pageTitle", "Delete actor");

        return REDIRECT+ACTORS;
    }

}