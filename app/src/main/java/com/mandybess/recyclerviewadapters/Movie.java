package com.mandybess.recyclerviewadapters;

import java.util.ArrayList;
import java.util.List;

public class Movie {

  protected String title;
  protected int duration;

  public static Movie makeDummyMovie() {
    Movie movie = new Movie();
    movie.title = "Movie Title";
    movie.duration = 90;
    return movie;
  }

  public static Movie makeDummyMovie(int count) {
    Movie movie = new Movie();
    movie.title = "Movie: " + count;
    movie.duration = 90;
    return movie;
  }

  public static List<Movie> makeMovies(int count) {
    List<Movie> items = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      items.add(Movie.makeDummyMovie(i));
    }
    return items;
  }
}
