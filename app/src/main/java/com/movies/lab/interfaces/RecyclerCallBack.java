package com.movies.lab.interfaces;


import com.movies.lab.enums.RecyclerEventType;

/**
 * Created by M.Noor
 */
public interface RecyclerCallBack {
    void onEventOccur(RecyclerEventType eventType, int position, Object object);
}
