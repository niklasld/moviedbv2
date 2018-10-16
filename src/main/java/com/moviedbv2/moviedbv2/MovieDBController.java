package com.moviedbv2.moviedbv2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.logging.Logger;
//autowired?
@Controller
public class MovieDBController {

    private final String INDEX = "index";
    private final String CREATEMOVIE = "createmovie";
    private final String DISPLAY = "display";
    private final String CREATEACTOR = "createactor";
    private final String EDITMOVIE = "editmovie";
    private final String EDITACTOR = "editactor";
    private final String DELETEMOVIE = "deletemovie";
    private final String DELETEACTOR = "deleteactor";

    Logger log = Logger.getLogger(MovieDBController.class.getName());

    HashMap<Integer, Movie> movies = new HashMap<>();
    HashMap<Integer, Actor> actors = new HashMap<>();

    public MovieDBController() {

    }

    @GetMapping("/")
    public String index(Model model) {
        log.info("Index called...");

        //model.addAllAttributes("movies",movies);

        return INDEX;
    }

    @GetMapping("/createMovie")
    public String createMovie(Model model) {
        log.info("createMovie getmapping called...");
        //model.addAllAttributes("movies", new Movie());

        return CREATEMOVIE;
    }

    @GetMapping("display/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        log.info("Display called, id="+id);

        return DISPLAY;
    }

    @GetMapping("/createActor")
    public String createActor(Model model) {
        log.info("Create actor called...");

        return CREATEACTOR;
    }

    @GetMapping("/editMovie")
    public String editMovie(Model Model) {
        log.info("Edit movie called...");

        return EDITMOVIE;
    }

    @GetMapping("/editActor")
    public String editActor(Model model) {
        log.info("Edit actor Called");

        return EDITACTOR;
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable("id") int id, Model model) {
        log.info("Delete movie called id="+id);

        return DELETEMOVIE;
    }

    @GetMapping("/deleteActor/{id}")
    public String deleteActor(@PathVariable("id") int id, Model model) {
        log.info("delete actor called id = "+id);

        return DELETEACTOR;
    }
}
