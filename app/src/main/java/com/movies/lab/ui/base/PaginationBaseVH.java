package com.movies.lab.ui.base;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Noor aka Thor.
 * to support a loading item and other item views.
 */
public class PaginationBaseVH extends RecyclerView.ViewHolder {
    public PaginationBaseVH(ViewDataBinding binding) {
        super(binding.getRoot());
    }
}
