package com.movies.lab.network.rx;

import android.util.Log;

import com.google.gson.JsonObject;
import com.movies.lab.network.exceptions.ServerException;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Noor aka Thor on 2020-04-08.
 */
public class RxHelper {
    private static final String TAG = "RxHelper";

    public static final String HTTP_ERROR = "Network error, please try again later.";

    public static <T> Observable.Transformer<JsonObject, T> handleResult() {
        return new Observable.Transformer<JsonObject, T>() {
            @Override
            public Observable<T> call(Observable<JsonObject> responseObservable) {
                return responseObservable.flatMap(new Func1<JsonObject, Observable<T>>() {
                    @Override
                    public Observable<T> call(JsonObject responseBody) {
                        Log.e(TAG, "Inside flat map");
                        if (responseBody != null) {
                            return createData((T)responseBody);
                        }

                        return Observable.error(new ServerException(HTTP_ERROR));
                    }
                });
            }
        };
    }

    private static <T> Observable<T> createData(T t) {
        return Observable.unsafeCreate(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
