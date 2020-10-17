package com.movies.lab.ui.movie.detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.movies.lab.R;
import com.movies.lab.databinding.ItemGenreBinding;
import com.movies.lab.ui.movie.model.Genre;

import java.util.List;

/**
 * Created by Noor aka Thor on 10/17/20.
 */
public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreVH> {

    private List<Genre> genres;

    public GenreAdapter(List<Genre> genres) {
        this.genres = genres;
    }

    @NonNull
    @Override
    public GenreVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGenreBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_genre, parent, false);
        return new GenreVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreVH holder, int position) {
        holder.binding.setGenre(genres.get(position));
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public static class GenreVH extends RecyclerView.ViewHolder {
        ItemGenreBinding binding;
        public GenreVH(ItemGenreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
