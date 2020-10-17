package com.movies.lab.network.rx;

import com.google.gson.JsonObject;
import com.movies.lab.constants.ApiConstants;
import com.movies.lab.network.exceptions.NoInternetException;
import com.movies.lab.network.exceptions.ServerException;
import com.movies.lab.utils.LogHelper;

import retrofit2.HttpException;
import rx.Subscriber;

/**
 * Created by pengwu on 16/11/23.
 * <p>
 * ServerException 是后台返回的错误状态，比如密码错误之类的
 * HttpException 是系统级的错误
 */

public abstract class RxSubscribe<T> extends Subscriber<T> {

    private static final String TAG = "Response result";

    @Override
    public void onNext(T t) {
        if (t instanceof JsonObject) {
            JsonObject responseBody = (JsonObject) t;
            _onNext(responseBody);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof NoInternetException) {
            NoInternetException noInternet = (NoInternetException) e;
            _onError(ApiConstants.EXCEPTION_NO_INTERNET, noInternet.getMessage());
        } else {
            LogHelper.e(TAG, e);
            if (e instanceof HttpException) {
                HttpException exception = (HttpException) e;
//                exception.response.errorBody().string()  //i figure out if still cannot do about error then can use this to get response json string
                _onError(String.valueOf(exception.code()), exception.message());
            } else if (e instanceof ServerException) {
                ServerException exception = (ServerException) e;
                _onError(exception.getMessageCode(), exception.getMessage());
            }
        }

    }

    public abstract void _onNext(JsonObject responseBody);

    public abstract void _onError(String messageCode, String message);

}
