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

    @Autowired
    JdbcTemplate template;

    @Override
    public Actor createActor(Actor actor) {
        String sql = "INSERT INTO actors VALUE(default, ?, ?)";
        String firstName = actor.getFirstName();
        String lastName = actor.getLastName();

        log.info("create actor" + firstName + lastName);
        this.template.update(sql, firstName, lastName);

        return actor;
    }

    @Override
    public Actor updateActor(Actor actor) {

        String sql = "UPDATE actors SET firstName = ?, lastName = ? WHERE actorId = ?";
        String firstName = actor.getFirstName();
        String lastName = actor.getLastName();

        int actorId = actor.getActorId();

        this.template.update(sql, firstName, lastName, actorId);

        return actor;
    }

    @Override
    public void deleteActor(int id) {

    }

    @Override
    public Actor findActor(int id) {
        return null;
    }

    @Override
    public List<Actor> searchActor(String actor) {
        return null;
    }

}
