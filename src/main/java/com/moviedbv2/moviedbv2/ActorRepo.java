package com.moviedbv2.moviedbv2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ActorRepo implements ActorRepoFace {

    @Autowired
    JdbcTemplate template;

    Logger log = Logger.getLogger(ActorRepo.class.getName());

    @Override
    public List<Actor> getActors() {
        String sql = "SELECT * FROM actors";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Actor>>(){

            @Override
            public List<Actor> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int actorId;
                String firstName, lastName;
                ArrayList<Actor> actors = new ArrayList<>();

                while(rs.next()){
                    actorId = rs.getInt("actorId");
                    firstName = rs.getString("firstName");
                    lastName = rs.getString("lastName");

                    actors.add(new Actor(actorId, firstName, lastName));
                }
                return actors;
            }
        });

    }

    @Override
    public List<Actor> actorsNotRelatedToMovie(int movieId) {
        String sql = "SELECT actors.actorId, firstName, lastName FROM actors\n" +
                "INNER JOIN actormovierelations ON actors.actorId = actormovierelations.fk_actorId\n" +
                "WHERE fk_movieId <> ?";

        // Fra sql til list.
        // Manuelt i stedet.
        return this.template.query(sql, new ResultSetExtractor<List<Actor>>(){

            @Override
            public List<Actor> extractData(ResultSet rs) throws SQLException, DataAccessException {
                int actorId;
                String firstName, lastName;
                ArrayList<Actor> actors = new ArrayList<>();

                while(rs.next()){
                    actorId = rs.getInt("actorId");
                    firstName = rs.getString("firstName");
                    lastName = rs.getString("lastName");

                    actors.add(new Actor(actorId, firstName, lastName));
                }
                return actors;
            }
        }, movieId);

    }

    @Override
    public Actor createActor(Actor actor) {

        String sql = "INSERT INTO actors VALUE(default, ?, ?)";
        String firstName= actor.getFirstName();
        String lastName= actor.getLastName();

        log.info("create actor" + firstName + lastName);
        this.template.update(sql, firstName, lastName);

        return actor;
    }

   /*@Override
    public List<Actor> getActors() {
        return null;
    }*/

    /*@Override
    public Actor createActor(Actor actor) {
        return null;
    }*/

    @Override
    public Movie updateActor(Actor actor) {
        return null;
    }

    @Override
    public void deleteActor(int id) {

    }

    @Override
    public Movie findActor(int id) {
        return null;
    }

    @Override
    public List<Movie> searchActor(String actor) {
        return null;
    }

    /*@Override
    public void addActorMovieRelation(int movieId, int actorId) {

        String sql = "INSET INTO actormovierelations VALUE(default, ?, ?)";

        log.info("creating actor movie relations movieId="+movieId+" actorId="+actorId);
        this.template.update(sql, movieId, actorId);
    }*/

    public void addActorMovieRelation(int movieId, int actorId) {
        String sql = "INSERT INTO actormovierelations VALUE(default, ?, ?)";

        /*
        int movieId = actorMovieRelation.getMovieId();
        int actorId = actorMovieRelation.getActorId();
        */
        log.info("creating actor movie relations movieId="+movieId+" actorId="+actorId);

        this.template.update(sql,movieId,actorId);

    }

}
