package com.moviedbv2.moviedbv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.moviedbv2.moviedbv2"})
@SpringBootApplication
public class Moviedbv2Application {

    public static void main(String[] args) {
        SpringApplication.run(Moviedbv2Application.class, args);
    }
}
