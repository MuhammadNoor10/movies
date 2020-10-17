package com.movies.lab.dagger.base;

import com.movies.lab.dagger.main.MainScope;
import com.movies.lab.ui.base.BaseActivity;
import com.movies.lab.ui.movie.detail.MovieDetailActivity;
import com.movies.lab.ui.movie.list.MovieListActivity;
import com.movies.lab.ui.movie.list.MoviesListVMsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Noor aka Thor.
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract BaseActivity contributeBaseActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = MoviesListVMsModule.class
    )
    abstract MovieListActivity contributeMainActivity();

    @MainScope
    @ContributesAndroidInjector
    abstract MovieDetailActivity contributeMovieDetailActivity();
}

























