package br.com.jairrb.mvvmjson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoviesResponse {
    @SerializedName("movies")
    @Expose
    private List<Movie> movies = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     *
     */
    public MoviesResponse() {
    }

    /**
     *
     * @param movies
     */
    public MoviesResponse(List<Movie> movies) {
        super();
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
