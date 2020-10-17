package com.movies.lab.common.android.arch.resource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Basic UIState that holds the Throwable being passed as error
 */
public class UIState {
    private final ResourceState state;
    @Nullable
    private final Throwable error;

    public UIState(@NonNull ResourceState state) {
        this(state, null);
    }

    public UIState(@NonNull ResourceState state, @Nullable Throwable error) {
        this.state = state;
        this.error = error;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    @NonNull
    public ResourceState getState() {
        return state;
    }

}
