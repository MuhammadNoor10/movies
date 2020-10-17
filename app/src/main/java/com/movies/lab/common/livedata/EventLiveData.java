package com.movies.lab.common.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

/**
 * @author Noor aka Thor
 */
public class EventLiveData extends LiveData<Object> {
    private final int subject;

    public EventLiveData(@LiveDataBus.Subject int subject) {
        this.subject = subject;
    }

    public void update(Object object) {
        postValue(object);
    }

    public void removeObserver(@NonNull Observer<Object> observer) {
        super.removeObserver(observer);
        if (!hasObservers()) {
            LiveDataBus.unregister(subject);
        }
    }
}
