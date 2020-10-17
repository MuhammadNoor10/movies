package com.movies.lab.network;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiRequestI {

    /**
     * @param url end point of api
     */
    @GET
    Observable<JsonObject> post(@Url String url);
}









































