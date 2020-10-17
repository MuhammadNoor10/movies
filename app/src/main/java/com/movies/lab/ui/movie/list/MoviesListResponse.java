package com.movies.lab.ui.movie.list;

import com.movies.lab.ui.movie.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noor aka Thor on 10/17/20.
 */
public class MoviesListResponse {
    private int page;
    private int total_results;
    private int total_pages;
    private List<Movie> results;

    public MoviesListResponse() {
        results = new ArrayList<>();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
