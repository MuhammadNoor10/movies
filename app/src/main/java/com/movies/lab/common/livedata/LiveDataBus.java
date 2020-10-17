package com.movies.lab.common.livedata;

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Noor aka Thor
 */
public final class LiveDataBus {
    public static final int SUBJECT_UPDATE_WALLET_BALANCE = 1;
    public static final int SUBJECT_UPDATE_WALLET_INFO = 2;

    private static final SparseArray<EventLiveData> subjectMap = new SparseArray<>();

    @Retention(RetentionPolicy.SOURCE)
    @interface Subject {
    }

    private LiveDataBus() {
        // hidden constructor
    }

    /**
     * Get the live data or create it if it's not already in memory.
     */
    @NonNull
    private static EventLiveData getLiveData(@Subject int subjectCode) {
        synchronized (subjectMap) {
            EventLiveData liveData = subjectMap.get(subjectCode);
            if (liveData == null) {
                liveData = new EventLiveData(subjectCode);
                subjectMap.put(subjectCode, liveData);
            }
            return liveData;
        }
    }

    /**
     * Subscribe to the specified subject and listen for updates on that subject.
     */
    public static void subscribe(@Subject int subject, @NonNull LifecycleOwner owner, @NonNull Observer<Object> action) {
        getLiveData(subject).observe(owner, action);
    }

    /**
     * Removes this subject when it has no observers.
     */
    public static void unregister(@Subject int subject) {
        subjectMap.remove(subject);
    }

    /**
     * Publish an object to the specified subject for all subscribers of that subject.
     */
    public static void publish(@Subject int subject, @NonNull Object message) {
        getLiveData(subject).update(message);
    }
}
