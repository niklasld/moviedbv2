package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class ActorController {

    @Autowired
    ActorRepoFace actorRepoFace;

    private final String ACTORS= "actors";
    private final String CREATEACTOR = "createactor";
    private final String EDITACTOR = "editactor";
    private final String DELETEACTOR = "deleteactor";
    private final String ADDACTORTOMOVIE = "addactortomovie";
    private final String ADDACTORTOMOVIECONFIRM = "addactortomovieconfirm";

    public ActorController() {

    }

    Logger log = Logger.getLogger(ActorController.class.getName());

    @GetMapping("/actors")
    public String actors(Model model) {
        log.info("actors called...");
        //movies = movieDBService.fetchAll();

        List<Actor> actors = actorRepoFace.getActors();
        model.addAttribute("actors", actors);

        return ACTORS;
    }


    @GetMapping("/createActor")
    public String createActor (Model model){
        log.info("createActor getmapping called...");
        model.addAttribute("actor", new Actor());

        return CREATEACTOR;
    }

    @PostMapping("/createActor")
    public String createActor(@ModelAttribute Actor actor, Model model) {
        log.info("Create actor called...");
        actorRepoFace.createActor(actor);
        model.addAttribute("actor",actorRepoFace.getActors());
        return "redirect:/actors";
    }

    @GetMapping("/addactortomovie/{movieId}")
    public String addActorToMovie(@PathVariable("movieId") int movieId, Model model) {
        log.info("Add actor to movie called for movie id: "+ movieId);

        List<Actor> actors = actorRepoFace.actorsNotRelatedToMovie(movieId);

        model.addAttribute("movieId", movieId);
        model.addAttribute("actors", actors);

        return ADDACTORTOMOVIE;
    }

    @GetMapping ("/addactortomovie/{movieId}/{actorId}")
    public String addActorToMovie(@PathVariable("movieId") int movieId, @PathVariable("actorId") int actorId, Model model) {
        log.info("Creating actor movie relation");

        model.addAttribute("addMovieRelation", new ActorMovieRelation());
        actorRepoFace.addActorMovieRelation(movieId,actorId);
        //log.info("actor movie relation confirm test check MovieId="+movieId+" actorid="+actorId);

        return "redirect:/";
    }

/*    public String addActorToMovieConfirm(@ModelAttribute ActorMovieRelation actorMovieRelation) {
        log.info("adding actor to movie, it have been confirmed");
        log.info("test"+actorMovieRelation.getActorId()+"test2"+actorMovieRelation.getMovieId());
        actorRepoFace.addActorMovieRelation(actorMovieRelation);

        return "redirect:/";
    }*/

}
