package com.movies.lab.app;

import com.movies.lab.dagger.app.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by Noor aka Thor.
 */
public class MoviesLabApp extends DaggerApplication {

    private static MoviesLabApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MoviesLabApp getApp() {
        return instance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
