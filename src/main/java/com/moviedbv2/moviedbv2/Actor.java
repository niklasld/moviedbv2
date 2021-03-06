package com.moviedbv2.moviedbv2;

public class Actor {

    private int actorId;
    private String firstName, lastName;

    public Actor() {
    }

    public Actor(int actorId, String firstName, String lastName) {
        this.actorId= actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int id) {this.actorId = id;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
