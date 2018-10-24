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

@Repository
public class ActorRepo implements ActorRepoFace {

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
    public Movie createActor(Actor actor) {
        return null;
    }

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

}
