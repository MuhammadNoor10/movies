package com.movies.lab.network;

import com.google.gson.JsonObject;
import com.movies.lab.constants.ApiConstants;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Noor aka Thor.
 */
@Singleton
public class ApiWrapper {

    private ApiTool apiTool;

    @Inject
    public ApiWrapper(ApiTool apiTool) {
        this.apiTool = apiTool;
    }

    public Observable<JsonObject> getMovies(int pageNumber) {
        return apiTool.post(ApiConstants.API_GET_MOVIES + pageNumber);
    }

    public Observable<JsonObject> getMovieDetail(int movieId) {
        return apiTool.post(ApiConstants.API_GET_MOVIE_DETAIL + movieId + "?api_key=328c283cd27bd1877d9080ccb1604c91");
    }
}





















