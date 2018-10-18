package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.logging.Logger;


@Controller
public class MovieDBController {

    @Autowired
    MovieDBRepoFace movieDBRepoFace;
    MovieDBServiceFace movieDBServiceFace;

    private final String INDEX = "index";
    private final String CREATEMOVIE = "createmovie";
    private final String DISPLAY = "display";
    private final String CREATEACTOR = "createactor";
    private final String EDITMOVIE = "editmovie";
    private final String EDITACTOR = "editactor";
    private final String DELETEMOVIE = "deletemovie";
    private final String DELETEACTOR = "deleteactor";

    Logger log = Logger.getLogger(MovieDBController.class.getName());

    //HashMap<Integer, Movie> movies = new HashMap<>();
    //HashMap<Integer, Actor> actors = new HashMap<>();

    public MovieDBController() {


    }

    @GetMapping("/")
    public String index(Model model) {
        log.info("Index called...");
        //movies = movieDBService.fetchAll();

        List<Movie> movies = movieDBRepoFace.getMovies();
        model.addAttribute("movies", movies);

        return INDEX;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(@RequestParam("movieTitle")String title, Model model){
        log.info("search title: " + title);

        List<Movie> movies = movieDBRepoFace.searchMovie(title);
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/createMovie")
    public String createMovie(Model model) {
        log.info("createMovie getmapping called...");
        //model.addAllAttributes("movie", new Movie());

        return CREATEMOVIE;
    }

    @PostMapping("/createmovie")
    public String createMovie(@ModelAttribute Movie movie, Model model){
        log.info("create movie postmapping called");
        //movieDBService.save(movie);
        //model.addAttribute("movie",movieDBService.fetchAll());
        return INDEX;
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
