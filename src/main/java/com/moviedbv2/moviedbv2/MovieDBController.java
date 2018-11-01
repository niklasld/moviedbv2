package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.logging.Logger;


@Controller
public class MovieDBController {

    @Autowired
    MovieDBServiceFace movieDBServiceFace;

    User loggedIn = new User();

    private final String REDIRECT = "redirect:/";
    private final String INDEX = "index";
    private final String CREATEMOVIE = "createmovie";

    private final String DISPLAY = "display";
    private final String EDITMOVIE = "editmovie";
    private final String DELETEMOVIE = "deletemovie";
    private final String ADDACTORTOMOVIE = "addActorToMovie";

    private final String EDITUSER = "edituser";

    private final String LOGIN = "login";

    Logger log = Logger.getLogger(MovieDBController.class.getName());

    public MovieDBController() {


    }

    @GetMapping("/")
    public String index(Model model) {
        log.info("Index called...");

        List<Movie> movies = movieDBServiceFace.getMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("pageTitle", "index");
        model.addAttribute("isMovies", true);
        if(loggedIn.getUserState() == 1) {
            model.addAttribute("isLoggedin", true);
        }

        return INDEX;
    }

    @GetMapping("/login")
    public String login(Model model) {
        log.info("login called...");

        model.addAttribute("users", new User());
        model.addAttribute("pageTitle", "Login");
        model.addAttribute("isLogin", true);

        return LOGIN;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, RedirectAttributes redirAttr) {
        boolean loginMatch = false;
        loginMatch = movieDBServiceFace.loginMatch(user);

        if(loginMatch == true) {
            redirAttr.addFlashAttribute("loginsuccess", true);
            redirAttr.addFlashAttribute("userName", user.getUserName());

            loggedIn = movieDBServiceFace.loggedIn(user);

            return REDIRECT;
        }
        else {

            redirAttr.addFlashAttribute("loginError", true);

            return REDIRECT + LOGIN;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(@RequestParam("movieTitle")String search, Model model){
        log.info("search title: " + search);

        List<Movie> movies = movieDBServiceFace.searchMovie(search);

        model.addAttribute("movies", movies);
        model.addAttribute("pageTitle", "Search for: " + search);


        return INDEX;
    }

    @GetMapping("/addActorToMovie")
    public String addActorToMovie(int movieId, Model model) {
        log.info("Add actor to movie called for movie id: "+ movieId);

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

        if(loggedIn.getUserState() == 1) {
            movieDBServiceFace.createMovie(movie);
        } else {
            log.info("You shall not pass here!");
        }

        model.addAttribute("movies", movieDBServiceFace.getMovies());
        model.addAttribute("pageTitle", "Create movie");

        return REDIRECT;
    }




    @GetMapping("display/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        log.info("Display called, id=" + id);

        model.addAttribute(("movie"), movieDBServiceFace.findMovie(id));
        model.addAttribute(("relatedActors"), movieDBServiceFace.getRelatedMovieActor(id));


        model.addAttribute("pageTitle", "Movie details");

        return DISPLAY;
    }


        @GetMapping("/editMovie/{id}")
        public String editMovie (@PathVariable Integer id, Model model){
            log.info("Edit movie called..." + id);

            model.addAttribute("movie", movieDBServiceFace.findMovie(id));

            model.addAttribute("relatedActors", movieDBServiceFace.getRelatedMovieActor(id));
            model.addAttribute("unrelatedActors", movieDBServiceFace.getUnrelatedMovieActor(id));

            String movieTitle = movieDBServiceFace.findMovie(id).getMovieTitle();
            model.addAttribute("pageTitle", "Edit movie (" + movieTitle + ")");
            model.addAttribute("movieTitle", movieTitle);

            return EDITMOVIE;
        }


        @RequestMapping(value = "/removerelation", method = RequestMethod.POST)
        public String removeRelation ( @RequestParam("movieId") int movieId, @RequestParam("actorId") int actorId, Model
        model){
            log.info("Remove relation called");

            movieDBServiceFace.removeRelation(actorId, movieId);

            model.addAttribute("movie", movieDBServiceFace.findMovie(movieId));

            model.addAttribute("relatedActors", movieDBServiceFace.getRelatedMovieActor(movieId));
            model.addAttribute("unrelatedActors", movieDBServiceFace.getUnrelatedMovieActor(movieId));

            String movieTitle = movieDBServiceFace.findMovie(movieId).getMovieTitle();
            model.addAttribute("pageTitle", "Edit movie (" + movieTitle + ")");
            model.addAttribute("movieTitle", movieTitle);
        return REDIRECT + "editMovie" + "/" + movieId;
    }

        @RequestMapping(value = "/addrelation", method = RequestMethod.POST)
        public String addRelation ( @RequestParam("movieId") int movieId, @RequestParam("actorId") int actorId, Model
        model){
            log.info("Add relation called");

            movieDBServiceFace.createRelation(actorId, movieId);

            model.addAttribute("movie", movieDBServiceFace.findMovie(movieId));

            model.addAttribute("relatedActors", movieDBServiceFace.getRelatedMovieActor(movieId));
            model.addAttribute("unrelatedActors", movieDBServiceFace.getUnrelatedMovieActor(movieId));

            String movieTitle = movieDBServiceFace.findMovie(movieId).getMovieTitle();
            model.addAttribute("pageTitle", "Edit movie (" + movieTitle + ")");
            model.addAttribute("movieTitle", movieTitle);
            return REDIRECT + "editMovie" + "/" + movieId;
    }

        @GetMapping("/deleteMovie/{id}")
        public String deleteMovie (@PathVariable Integer id, Model model){
            log.info("Delete movie wits id: " + id + "?");

            model.addAttribute("movie", movieDBServiceFace.findMovie(id));
            String movieTitle = movieDBServiceFace.findMovie(id).getMovieTitle();
            model.addAttribute("pageTitle", "Delete movie (" + movieTitle + ")");

            return DELETEMOVIE;
        }

        @PutMapping("/deleteMovie")
        public String delete (@ModelAttribute Movie movie, Model model){
            log.info("delete confirmed deleting movie " + movie.getMovieId());
            int id = movie.getMovieId();

            movieDBServiceFace.deleteMovie(id);

            model.addAttribute("movies", movieDBServiceFace.getMovies());
            model.addAttribute("pageTitle", "Delete movie");

            return REDIRECT;
        }

        @PutMapping("/editmovie")
        public String editMovie (@ModelAttribute Movie movie, Model model){

            movieDBServiceFace.updateMovie(movie);

            model.addAttribute("movies", movieDBServiceFace.getMovies());
            model.addAttribute("pageTitle", "Edit movie");

            return REDIRECT;
        }

        @GetMapping("/edituser/{userId}")
        public String editUser (@PathVariable("userId") int userId, Model model) {

            log.info("Edit user called with id="+userId);

            model.addAttribute("user",movieDBServiceFace.findUser(userId));
            model.addAttribute("pageTitle", "Edit User");

            return EDITUSER;
        }

        @PutMapping("/edituser")
        public String editUser(@ModelAttribute User user, Model model) {
            movieDBServiceFace.updateUser(user);

            model.addAttribute("pageTitle", "Edit User");

            return REDIRECT;
        }
    }