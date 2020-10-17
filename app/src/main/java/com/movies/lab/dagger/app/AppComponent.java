package com.movies.lab.dagger.app;

import android.app.Application;

import com.movies.lab.app.MoviesLabApp;
import com.movies.lab.dagger.base.ActivityBuildersModule;
import com.movies.lab.dagger.network.ApiModule;
import com.movies.lab.dagger.viewmodel.ViewModelFactoryModule;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Noor aka Thor.
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuildersModule.class,
        ApiModule.class,
        ViewModelFactoryModule.class,
})
public interface AppComponent extends AndroidInjector<MoviesLabApp> {
    void inject(MoviesLabApp application);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
