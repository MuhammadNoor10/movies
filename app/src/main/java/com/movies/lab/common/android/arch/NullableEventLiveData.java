package com.movies.lab.common.android.arch;

import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


/**
 * {@link Nullable} value LiveData.
 * Use {@link #consume()} to manually consume events.
 * <p>
 * Additionally, use {@link #observeConsuming} to automatically consume the typed {@link T} event after
 * their values are reflected to the observers.
 *
 * @param <T> Event type. Nullable.
 */
public class NullableEventLiveData<T> extends MutableLiveData<T> {

    public NullableEventLiveData() {
        super();
    }

    public NullableEventLiveData(@Nullable T initialValue) {
        super(initialValue);
    }

    @MainThread
    public void consume() {
        setValue(null);
    }

    @AnyThread
    public void postConsume() {
        postValue(null);
    }

    @MainThread
    public void observeConsuming(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, t -> {
            observer.onChanged(t);
            if (t != null) consume();
        });

    }

    @MainThread
    public void observePostConsuming(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, t -> {
            observer.onChanged(t);
            if (t != null) postConsume();
        });
    }
}
