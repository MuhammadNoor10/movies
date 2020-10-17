package com.movies.lab.ui.movie.list;

import com.google.gson.JsonObject;
import com.movies.lab.BuildConfig;
import com.movies.lab.common.android.arch.resource.UIDataState;
import com.movies.lab.common.android.rx.RxViewModel;
import com.movies.lab.network.ApiWrapper;
import com.movies.lab.ui.base.BaseUIState;

import javax.inject.Inject;
import rx.Single;
import rx.Subscription;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Noor aka Thor.
 */
public class MoviesListVM extends RxViewModel {

    private ApiWrapper apiWrapper;
    public BaseUIState getMoviesState;
    public BaseUIState getMovieDetailState;

    @Inject
    public MoviesListVM(ApiWrapper apiWrapper, BaseUIState getMoviesState, BaseUIState getMovieDetailState) {
        this.apiWrapper = apiWrapper;
        this.getMoviesState = getMoviesState;
        this.getMovieDetailState = getMovieDetailState;
    }

    public void getMovies(int pageNumber) {
        UIDataState state = getMoviesState.getValue();
        if (state == null || !state.isLoading()) {
            getMoviesState.postValue(UIDataState.loading());

            Subscription subscription = Single.defer(() -> apiWrapper.getMovies(pageNumber).toSingle())
                    .flatMap((Func1<JsonObject, Single<?>>) Single::just)
                    .subscribeOn(Schedulers.io())
                    .subscribe(responseBody -> getMoviesState.postValue(UIDataState.success(responseBody))
                            , error -> getMoviesState.postValue(UIDataState.error(error)));
            compositeDisposable.add(subscription);
        }
    }

    public void getMovieDetail(int movieId) {
        UIDataState state = getMovieDetailState.getValue();
        if (state == null || !state.isLoading()) {
            getMovieDetailState.postValue(UIDataState.loading());

            Subscription subscription = Single.defer(() -> apiWrapper.getMovieDetail(movieId).toSingle())
                    .flatMap((Func1<JsonObject, Single<?>>) Single::just)
                    .subscribeOn(Schedulers.io())
                    .subscribe(responseBody -> getMovieDetailState.postValue(UIDataState.success(responseBody))
                            , error -> getMovieDetailState.postValue(UIDataState.error(error)));

            compositeDisposable.add(subscription);
        }
    }
}
