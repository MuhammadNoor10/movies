package com.movies.lab.ui.movie.list;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import com.google.gson.JsonObject;
import com.movies.lab.R;
import com.movies.lab.common.android.arch.NonNullObserver;
import com.movies.lab.common.android.arch.resource.UIDataState;
import com.movies.lab.dagger.viewmodel.ViewModelProviderFactory;
import com.movies.lab.databinding.ActivityMoviesListBinding;
import com.movies.lab.enums.RecyclerEventType;
import com.movies.lab.ui.base.BaseActivity;
import com.movies.lab.ui.movie.detail.MovieDetailActivity;
import com.movies.lab.ui.movie.model.Movie;
import com.movies.lab.utils.JsonUtil;
import com.movies.lab.widgets.MyToast;
import com.movies.lab.widgets.PaginationListener;

import java.util.ArrayList;

import javax.inject.Inject;

public class MovieListActivity extends BaseActivity {

    @Inject
    ViewModelProviderFactory vmf;
    @Inject
    MyToast toast;

    private ActivityMoviesListBinding binding;
    private MoviesListAdapter adapter;

    //pagination
    private int currentPage = PaginationListener.PAGE_START;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private MoviesListVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies_list);
        init();
        viewModel.getMovies(currentPage);
    }

    private void init() {
        injectViewModel();
        initAdapter();
        binding.swipeRefresh.setOnRefreshListener(this::onRefresh);
    }

    private void injectViewModel() {
        viewModel = new ViewModelProvider(this, vmf).get(MoviesListVM.class);
        viewModel.getMoviesState.observeConsuming(this, (NonNullObserver<UIDataState>) this::getMoviesStateUpdate);
        viewModel.getMovieDetailState.observeConsuming(this, (NonNullObserver<UIDataState>) this::getMovieDetailStateUpdate);
    }

    private void initAdapter() {
        adapter = new MoviesListAdapter(new ArrayList<>(), this::onEventOccur);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvMovies.setLayoutManager(linearLayoutManager);
        binding.rvMovies.setAdapter(adapter);
        binding.rvMovies.addOnScrollListener(new PaginationListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                viewModel.getMovies(currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    //Recycler callback
    private void onEventOccur(RecyclerEventType recyclerEventType, int position, Object object) {
        viewModel.getMovieDetail(((Movie)object).getId());
    }

    /**
     * get movie api callback. Get detail and pass it on to next MovieListDetail Activity
     */
    private void getMovieDetailStateUpdate(UIDataState state) {
        if (state.isLoading()) {
                showProgressDialog(binding.getRoot().getContext());
        } else {
            hideProgressDialog();
            binding.swipeRefresh.setRefreshing(false);
        }

        if (state.isError()) {
            toast.setTextAndShow(state.getError().getMessage());
        } else if (state.isSuccessful()) {
            JsonObject jsonObject = (JsonObject) state.getData();
            Movie movie = JsonUtil.parseObject(jsonObject.toString(), Movie.class);
            MovieDetailActivity.start(this, movie);
        }
    }

    /**
     * get moves api callback.
     */
    private void getMoviesStateUpdate(UIDataState state) {
        if (state.isLoading()) {
            if (currentPage == 1) {
                showProgressDialog(binding.getRoot().getContext());
            }
        } else {
            hideProgressDialog();
            binding.swipeRefresh.setRefreshing(false);
        }

        if (state.isError()) {
            toast.setTextAndShow(state.getError().getMessage());
        } else if (state.isSuccessful()) {
            JsonObject jsonObject = (JsonObject) state.getData();
            MoviesListResponse moviesListResponse = JsonUtil.parseObject(jsonObject.toString(), MoviesListResponse.class);
            if (moviesListResponse != null && moviesListResponse.getResults() != null && !moviesListResponse.getResults().isEmpty()) {
                if (currentPage != PaginationListener.PAGE_START) adapter.removeLoading();
                adapter.addItems(moviesListResponse.getResults());

                // check weather is last page or not
                if (currentPage < moviesListResponse.getTotal_pages()) {
                    adapter.addLoading();
                } else {
                    isLastPage = true;
                }
                isLoading = false;
            }
        }
    }

    private void onRefresh() {
        resetPagination();
        viewModel.getMovies(currentPage);
    }

    /**
     * upon refresh mush reset pagination. So the pagination will act as new
     */
    private void resetPagination() {
        currentPage = PaginationListener.PAGE_START;
        isLastPage = false;
        adapter.clear();
    }
}





































