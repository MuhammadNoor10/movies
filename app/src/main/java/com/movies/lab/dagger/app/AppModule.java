package com.movies.lab.dagger.app;

import android.app.Application;
import android.content.Context;

import com.movies.lab.widgets.MyToast;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Noor aka Thor.
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    public Context provideAppContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    public MyToast provideMyToast(Application application) {
        return MyToast.getInstance(application.getApplicationContext());
    }
}
