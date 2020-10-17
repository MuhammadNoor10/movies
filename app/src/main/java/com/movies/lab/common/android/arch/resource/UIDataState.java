package com.movies.lab.common.android.arch.resource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Generic UIState that may holds the Throwable being passed as error or the generic result on success
 */
public class UIDataState<T> {
    @Nullable
    private final T data;
    @NonNull
    private final ResourceState state;
    @Nullable
    private final Throwable error;

    public UIDataState(@NonNull ResourceState state) {
        this(null, state, null);
    }

    public UIDataState(@NonNull Throwable error) {
        this(null, ResourceState.ERROR, error);
    }

    public UIDataState(@NonNull T data) {
        this(data, ResourceState.SUCCESS, null);
    }

    public UIDataState(@Nullable T data, @NonNull ResourceState state, @Nullable Throwable error) {
        this.state = state;
        this.error = error;
        this.data = data;
    }

    public static <T> UIDataState<T> ready() {
        return new UIDataState<>(ResourceState.READY);
    }

    public static <T> UIDataState<T> loading() {
        return new UIDataState<>(ResourceState.LOADING);
    }

    public static <T> UIDataState<T> error(Throwable error) {
        return new UIDataState<>(error);
    }

    public static <T> UIDataState<T> success(T data) {
        return new UIDataState<>(data);
    }

    public boolean isLoading() {
        return ResourceState.LOADING.equals(getState());
    }

    public boolean isSuccessful() {
        return ResourceState.SUCCESS.equals(getState());
    }

    public boolean isError() {
        return ResourceState.ERROR.equals(getState());
    }

    public boolean isReady() {
        return ResourceState.READY.equals(getState());
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    @NonNull
    public ResourceState getState() {
        return state;
    }

    @Nullable
    public T getData() {
        return data;
    }
}
