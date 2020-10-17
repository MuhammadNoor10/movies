package com.movies.lab.ui.base;


import com.movies.lab.common.android.arch.NullableEventLiveData;
import com.movies.lab.common.android.arch.resource.UIDataState;

import javax.inject.Inject;

/**
 * Created by Noor aka Thor.
 */
public class BaseUIState extends NullableEventLiveData<UIDataState> {
    @Inject
    BaseUIState() { }
}
