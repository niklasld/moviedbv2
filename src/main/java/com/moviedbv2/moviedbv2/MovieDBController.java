package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

import java.util.List;

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
    private final String ACTORS= "actors";
    private final String CREATEACTOR = "createactor";
    private final String EDITMOVIE = "editmovie";
    private final String EDITACTOR = "editactor";
    private final String DELETEMOVIE = "deletemovie";
    private final String DELETEACTOR = "deleteactor";

    Logger log = Logger.getLogger(MovieDBController.class.getName());

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
        model.addAttribute("movie", new Movie());

        return CREATEMOVIE;
    }

    @PostMapping("/createMovie")
    public String createMovie(@ModelAttribute Movie movie, Model model){
        log.info("create movie postmapping called");
        movieDBRepoFace.createMovie(movie);
        model.addAttribute("movies",movieDBRepoFace.getMovies());
        return "redirect:/";
    }

    @GetMapping("display/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        log.info("Display called, id="+id);

        return DISPLAY;
    }


    @GetMapping("/actors")
    public String actors(Model model) {
        log.info("actors called...");
        //movies = movieDBService.fetchAll();

        List<Actor> actors = movieDBRepoFace.getActors();
        model.addAttribute("actors", actors);

        return ACTORS;
    }


    @PostMapping("/createActor")
    public String createActor(@ModelAttribute Actor actor, Model model) {
        log.info("Create actor called...");
        movieDBRepoFace.createActor(actor);
        model.addAttribute("actor",movieDBRepoFace.getActors());
        return "redirect:/actors";
    }

    @GetMapping("/createActor")
    public String createActor (Model model){
        log.info("createActor getmapping called...");
        model.addAttribute("actor", new Actor());

        return CREATEACTOR;
    }


    @GetMapping("/editMovie/{id}")
    public String editMovie(@PathVariable Integer id, Model model) {
        log.info("Edit movie called..."+id);

        model.addAttribute("movie", movieDBRepoFace.findMovie(id));

        return EDITMOVIE;
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable Integer id, Model model) {
        log.info("Delete movie wits id: "+id+"?");
        model.addAttribute("movie", movieDBRepoFace.findMovie(id));
        return DELETEMOVIE;
    }

    @PutMapping("/deleteMovie")
    public String delete(@ModelAttribute Movie movie, Model model) {
        log.info("delete confirmed deleting movie "+movie.getMovieId());
        int id = movie.getMovieId();

        movieDBRepoFace.deleteMovie(id);

        model.addAttribute("movies", movieDBRepoFace.getMovies());
        return "redirect:/";
    }

    @PutMapping("/editmovie")
    public String editMovie(@ModelAttribute Movie movie, Model model){

    movieDBRepoFace.updateMovie(movie);

    model.addAttribute("movies", movieDBRepoFace.getMovies());
    return "redirect:/";
    }

    @GetMapping("/editActor")
    public String editActor(Model model) {
        log.info("Edit actor Called");
        return EDITACTOR;

    }
}
