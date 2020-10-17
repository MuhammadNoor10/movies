package com.movies.lab.ui.movie.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.movies.lab.R;
import com.movies.lab.databinding.ItemLoadingBinding;
import com.movies.lab.databinding.ItemMovieBinding;
import com.movies.lab.enums.RecyclerEventType;
import com.movies.lab.interfaces.RecyclerCallBack;
import com.movies.lab.ui.base.PaginationBaseVH;
import com.movies.lab.ui.movie.model.Movie;

import java.util.List;

/**
 * Created by Noor aka Thor.
 */
public class MoviesListAdapter extends RecyclerView.Adapter<PaginationBaseVH> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    private List<Movie> items;
    private RecyclerCallBack callBack;

    public MoviesListAdapter(List<Movie> items, RecyclerCallBack callBack) {
        this.items = items;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public PaginationBaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ItemViewHolder(
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie, parent, false));
            case VIEW_TYPE_LOADING:
                return new LoadingViewHolder(
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PaginationBaseVH holder, int position) {
        if (holder instanceof ItemViewHolder) {
            Movie movie = items.get(position);
            ((ItemViewHolder) holder).binding.setMovie(movie);
            Glide.with(((ItemViewHolder) holder).binding.getRoot()).load(movie.getPosterPath()).placeholder(R.drawable.poster_placeholder).into(((ItemViewHolder) holder).binding.ivPoster);
            ((ItemViewHolder) holder).binding.root.setOnClickListener(unused -> {
                callBack.onEventOccur(RecyclerEventType.ITEM_CLICK, position, movie);
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == items.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void addItems(List<Movie> postItems) {
        items.addAll(postItems);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        items.add(new Movie());
        notifyItemInserted(items.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = items.size() - 1;
        Movie item = getItem(position);
        if (item != null) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    Movie getItem(int position) {
        return items.get(position);
    }

    public void removeItem(int position) {
        if (items.size() > position) {
            items.remove(position);
            notifyDataSetChanged();
        }
    }

    public class LoadingViewHolder extends PaginationBaseVH {
        public ItemLoadingBinding binding;
        public LoadingViewHolder(ItemLoadingBinding binding) {
            super(binding);
            this.binding = binding;
        }
    }

    public class ItemViewHolder extends PaginationBaseVH {
        public ItemMovieBinding binding;
        public ItemViewHolder(ItemMovieBinding binding) {
            super(binding);
            this.binding = binding;
        }
    }

}
