package binay.bemoapplication.ui.fragments;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import binay.bemoapplication.R;
import binay.bemoapplication.api.PopularMoviesApi;
import binay.bemoapplication.generator.MovieApiGenerator;
import binay.bemoapplication.ui.adapter.HomeRecyclerAdapter;
import binay.bemoapplication.ui.interfaces.ItemClickListener;
import binay.bemoapplication.ui.model.MovieResponseModel;
import binay.bemoapplication.ui.model.MovieResultsModel;
import binay.bemoapplication.utils.Constants;
import binay.bemoapplication.utils.EndlessRecyclerOnScrollListener;
import binay.bemoapplication.utils.GridSpacesItemDecoration;
import binay.bemoapplication.utils.IntentUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieGridFragment extends Fragment {

    @BindView(R.id.recyclerGridView)
    RecyclerView recyclerGridView;
    @BindView(R.id.root)
    LinearLayout root;
    private GridLayoutManager staggeredGridLayoutManager;
    PopularMoviesApi getPopularMovieApi;
    private ArrayList<MovieResultsModel> moviesList;
    private HomeRecyclerAdapter rcAdapter;
    int page = 1;
    int sortOrder;
    String apiKey;

    ProgressDialog loadingDialog;
    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    ActivityOptions options = null;
    private ItemClickListener itemClickListener;
    MovieResultsModel model;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // the callback interface. If not, it throws an exception
        try {
            itemClickListener = (ItemClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.getClass().toString()
                    + " must implement ItemClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_grid_layout, container, false);
        ButterKnife.bind(this, rootView);
        getPopularMovieApi = MovieApiGenerator.createService(PopularMoviesApi.class);
        moviesList = new ArrayList<>();
        apiKey = Constants.API_KEY;
        if (savedInstanceState != null) {
            moviesList = (ArrayList<MovieResultsModel>) savedInstanceState.get(IntentUtils.MOVIE_LIST_SAVE_INSTANCE);
        }
        setupViews();
        return rootView;
    }


    private void setupViews() {
        recyclerGridView.setHasFixedSize(false);
        loadingDialog = new ProgressDialog(getActivity());
        loadingDialog.setMessage("Fetching movies");
        staggeredGridLayoutManager = new GridLayoutManager(getActivity(), (int) getResources().getInteger(R.integer.grid_size));
        recyclerGridView.setLayoutManager(staggeredGridLayoutManager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.rv_grid_spacing);
        recyclerGridView.addItemDecoration(new GridSpacesItemDecoration(spacingInPixels));
        rcAdapter = new HomeRecyclerAdapter(getActivity(), moviesList,itemClickListener);
        recyclerGridView.setAdapter(rcAdapter);
        if (getMyListData() != null && getMyListData().size() > 0) {
            rcAdapter.notifyDataSetChanged();
        } else {
            sortOrder = 01;
            fetchMovies(page);
        }
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                fetchMovies(current_page);
            }
        };
        recyclerGridView.addOnScrollListener(endlessRecyclerOnScrollListener);
    }


    private void fetchMovies(int page) {

        if (page == 1)
            loadingDialog.show();
        if (sortOrder == IntentUtils.POPULAR_SORT)
            getPopularMovieApi.getPopularMovies(page, apiKey, new Callback<MovieResponseModel>() {
                @Override
                public void success(MovieResponseModel movieResponseModel, Response response) {
                    loadingDialog.cancel();
                    moviesList.addAll(movieResponseModel.getResults());
                    rcAdapter.notifyDataSetChanged();
                }

                @Override
                public void failure(RetrofitError error) {
                    loadingDialog.cancel();
                }
            });
        else if (sortOrder == IntentUtils.HIGHEST_RATED_SORT)
            getPopularMovieApi.getPopularMovies(page, apiKey, new Callback<MovieResponseModel>() {
                @Override
                public void success(MovieResponseModel movieResponseModel, Response response) {
                    moviesList.addAll(movieResponseModel.getResults());
                    loadingDialog.cancel();
                    rcAdapter.notifyDataSetChanged();
                }

                @Override
                public void failure(RetrofitError error) {
                    loadingDialog.cancel();
                }
            });
        else {
            getFavMovies();
        }

    }

    private void getFavMovies() {
        /*try {
            List<MovieResultsModel> list = new RushSearch()
                    .find(MovieResultsModel.class);
            moviesList.clear();
            rcAdapter.notifyDataSetChanged();
            if (list.size() != 0) {
                moviesList.addAll(list);
                rcAdapter.notifyDataSetChanged();
            }
            loadingDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    // Fires when a configuration change occurs and fragment needs to save state
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(IntentUtils.MOVIE_LIST_SAVE_INSTANCE,
                (ArrayList<? extends Parcelable>) moviesList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void sortMovies(int sortOrder) {
        this.sortOrder = sortOrder;
        moviesList.clear();
        rcAdapter.notifyDataSetChanged();
        endlessRecyclerOnScrollListener.reset(0, true);
        page = 1;
        fetchMovies(page);
    }

    public void setMyListData(ArrayList<MovieResultsModel> data) {
        this.moviesList = data;
    }

    public ArrayList<MovieResultsModel> getMyListData() {
        return this.moviesList;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;

        do {
            if (view instanceof CoordinatorLayout) {
                return (ViewGroup) view;
            }

            if (view instanceof FrameLayout) {
                // android.R.id.content
                if (view.getId() == android.R.id.content) {
                    return (ViewGroup) view;
                }

                fallback = (ViewGroup) view;
            }

            if (view != null) {
                ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);

        return fallback;
    }


}
