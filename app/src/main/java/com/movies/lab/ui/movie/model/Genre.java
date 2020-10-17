package com.movies.lab.ui.movie.model;

import java.io.Serializable;

/**
 * Created by Noor aka Thor on 10/17/20.
 */
public class Genre implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
