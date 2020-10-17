package com.movies.lab.common.android.rx;

import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

import rx.subscriptions.CompositeSubscription;

public class RxViewModel extends ViewModel {

    protected final CompositeSubscription compositeDisposable;
    private final List<CompositeSubscription> compositeDisposables;

    public RxViewModel(CompositeSubscription... compositeDisposables) {
        this.compositeDisposables = Arrays.asList(compositeDisposables);
        this.compositeDisposable = new CompositeSubscription();

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        for (CompositeSubscription disposables : compositeDisposables) {
            disposables.clear();
        }
        compositeDisposable.clear();
    }
}
