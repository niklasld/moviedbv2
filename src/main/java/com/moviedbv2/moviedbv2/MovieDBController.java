package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@Controller
public class MovieDBController {

    @Autowired
    MovieDBRepoFace movieDBRepoFace;
    MovieDBServiceFace movieDBServiceFace;
    ActorRepoFace actorRepoFace;

    private final String INDEX = "index";
    private final String CREATEMOVIE = "createmovie";
    private final String DISPLAY = "display";
    private final String ACTORS= "actors";
    private final String CREATEACTOR = "createactor";
    private final String EDITMOVIE = "editmovie";
    private final String EDITACTOR = "editactor";
    private final String DELETEMOVIE = "deletemovie";
    private final String DELETEACTOR = "deleteactor";
    private final String ADDACTORTOMOVIE = "addActorToMovie";

    Logger log = Logger.getLogger(MovieDBController.class.getName());

    public MovieDBController() {


    }

    @GetMapping("/")
    public String index(Model model) {
        log.info("Index called...");
        //movies = movieDBService.fetchAll();

        List<Movie> movies = movieDBRepoFace.getMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("pageTitle", "index");
        model.addAttribute("isMovies", true);

        return INDEX;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(@RequestParam("movieTitle")String search, Model model){
        log.info("search title: " + search);

        List<Movie> movies = movieDBRepoFace.searchMovie(search);
        model.addAttribute("movies", movies);
        model.addAttribute("pageTitle", "Search for: " + search);


        return INDEX;
    }

    @GetMapping("/addActorToMovie")
    public String addActorToMovie(int movieId, Model model) {
        log.info("Add actor to movie called for movie id: "+ movieId);
        List<Actor> actors = actorRepoFace.getActors();
        return ADDACTORTOMOVIE;
    }

    @GetMapping("/createMovie")
    public String createMovie(Model model) {
        log.info("createMovie getmapping called...");

        model.addAttribute("movie", new Movie());
        model.addAttribute("pageTitle", "Create movie");

        return CREATEMOVIE;
    }

    @PostMapping("/createMovie")
    public String createMovie(@ModelAttribute Movie movie, Model model){
        log.info("create movie postmapping called");

        movieDBRepoFace.createMovie(movie);

        model.addAttribute("movies",movieDBRepoFace.getMovies());
        model.addAttribute("pageTitle", "Create movie");

        return "redirect:/";
    }

    @GetMapping("display/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        log.info("Display called, id="+id);


        model.addAttribute("pageTitle", "Movie details");

        return DISPLAY;
    }


    @GetMapping("/actors")
    public String actors(Model model) {
        log.info("actors called...");
        //movies = movieDBService.fetchAll();

        List<Actor> actors = movieDBRepoFace.getActors();
        model.addAttribute("actors", actors);
        model.addAttribute("isActors", true);

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

        model.addAttribute("relatedActors", movieDBRepoFace.getRelatedMovieActor(id));
        model.addAttribute("unrelatedActors", movieDBRepoFace.getUnrelatedMovieActor(id));

        String movieTitle = movieDBRepoFace.findMovie(id).getMovieTitle();
        model.addAttribute("pageTitle", "Edit movie (" + movieTitle + ")");
        model.addAttribute("movieTitle", movieTitle);

        return EDITMOVIE;
    }

    @RequestMapping(value = "/removerelation", method = RequestMethod.POST)
    public String removeRelation(@RequestParam("movieId") int movieId, @RequestParam("actorId") int actorId, Model model){
        //log.info("search title: " + search);

        movieDBRepoFace.removeRelation(actorId, movieId);

        model.addAttribute("movie", movieDBRepoFace.findMovie(movieId));

        model.addAttribute("relatedActors", movieDBRepoFace.getRelatedMovieActor(movieId));
        model.addAttribute("unrelatedActors", movieDBRepoFace.getUnrelatedMovieActor(movieId));

        String movieTitle = movieDBRepoFace.findMovie(movieId).getMovieTitle();
        model.addAttribute("pageTitle", "Edit movie (" + movieTitle + ")");
        model.addAttribute("movieTitle", movieTitle);

        return "redirect:/editMovie/" + movieId;
    }

    @RequestMapping(value = "/addrelation", method = RequestMethod.POST)
    public String addRelation(@RequestParam("movieId") int movieId, @RequestParam("actorId") int actorId, Model model){
        //log.info("search title: " + search);

        movieDBRepoFace.createRelation(actorId, movieId);

        model.addAttribute("movie", movieDBRepoFace.findMovie(movieId));

        model.addAttribute("relatedActors", movieDBRepoFace.getRelatedMovieActor(movieId));
        model.addAttribute("unrelatedActors", movieDBRepoFace.getUnrelatedMovieActor(movieId));

        String movieTitle = movieDBRepoFace.findMovie(movieId).getMovieTitle();
        model.addAttribute("pageTitle", "Edit movie (" + movieTitle + ")");
        model.addAttribute("movieTitle", movieTitle);

        return "redirect:/editMovie/" + movieId;
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable Integer id, Model model) {
        log.info("Delete movie wits id: "+id+"?");

        model.addAttribute("movie", movieDBRepoFace.findMovie(id));
        String movieTitle = movieDBRepoFace.findMovie(id).getMovieTitle();
        model.addAttribute("pageTitle", "Delete movie (" + movieTitle + ")");

        return DELETEMOVIE;
    }

    @PutMapping("/deleteMovie")
    public String delete(@ModelAttribute Movie movie, Model model) {
        log.info("delete confirmed deleting movie "+movie.getMovieId());
        int id = movie.getMovieId();

        movieDBRepoFace.deleteMovie(id);

        model.addAttribute("movies", movieDBRepoFace.getMovies());
        model.addAttribute("pageTitle", "Delete movie");

        return "redirect:/";
    }

    @PutMapping("/editmovie")
        public String editMovie(@ModelAttribute Movie movie, Model model){

        movieDBRepoFace.updateMovie(movie);

        model.addAttribute("movies", movieDBRepoFace.getMovies());
        model.addAttribute("pageTitle", "Edit movie");

        return "redirect:/";
    }

    @GetMapping("/editActor")
    public String editActor(Model model) {
        log.info("Edit actor Called");
        return EDITACTOR;

    }
}
