package com.movies.lab.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.movies.lab.app.MoviesLabApp;
import com.movies.lab.network.exceptions.NoInternetException;
import com.movies.lab.network.rx.RxHelper;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by M.Noor
 */
@Singleton
public class ApiTool {

    private ApiRequestI request;

    @Inject
    public ApiTool(ApiRequestI request) {
        this.request = request;
    }

    public Observable<JsonObject> post(@NonNull final String url) {
        return Observable.defer(() -> {
            if (!isOnline()) {
                return Observable.error(new NoInternetException());
            }

            return request.post(url)
                    .subscribeOn(Schedulers.io())
                    .compose(RxHelper.handleResult());
        });
    }

    private boolean isOnline() {
        Context context = MoviesLabApp.getApp().getBaseContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }
}
