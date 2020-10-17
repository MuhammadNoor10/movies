package com.movies.lab.ui.movie.list;

import androidx.lifecycle.ViewModel;

import com.movies.lab.dagger.viewmodel.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Noor aka Thor.
 */
@Module
public abstract class MoviesListVMsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MoviesListVM.class)
    public abstract ViewModel bindMoviesListVM(MoviesListVM MainVM);
}
