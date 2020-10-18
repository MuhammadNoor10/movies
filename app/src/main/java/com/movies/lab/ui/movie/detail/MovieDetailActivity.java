package com.movies.lab.ui.movie.detail;

import androidx.databinding.DataBindingUtil;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.movies.lab.R;
import com.movies.lab.databinding.ActivityMovieDetailBinding;
import com.movies.lab.ui.base.BaseActivity;
import com.movies.lab.ui.movie.model.Movie;
import com.movies.lab.widgets.MyToast;

import javax.inject.Inject;

public class MovieDetailActivity extends BaseActivity {
    private static final String ARGS_MOVIE = "ARGS_MOVIE";

    @Inject
    MyToast toast;

    private ActivityMovieDetailBinding binding;
    private Movie movie;

    public static void start(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(ARGS_MOVIE, movie);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        initToolbar();
        movie = (Movie) getIntent().getSerializableExtra(ARGS_MOVIE);
        if (movie != null) {
            binding.setMovie(movie); //data binding
            Glide.with(this).load(movie.getPosterPath()).placeholder(R.drawable.poster_placeholder).into(binding.ivPoster);
            Glide.with(this).load(movie.getBackdropPath()).placeholder(R.color.colorBlack).into(binding.ivLandscape);
            initGenreAdapter();
        } else { //in worst case failed to get movie object from intent
            toast.setTextAndShow("Failed to load data. Please try again");
            onBackPressed();
        }
    }

    /**
     * this genre adapter is using google flexbox lib. which enable to have a free style dynamic per row or column item limitation
     */
    private void initGenreAdapter() {
        GenreAdapter genreAdapter = new GenreAdapter(movie.getGenres());
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        binding.rvGenres.setLayoutManager(layoutManager);
        binding.rvGenres.setAdapter(genreAdapter);
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}



























