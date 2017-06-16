package binay.bemoapplication.ui.fragments;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.text.style.BulletSpan;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binaryfork.spanny.Spanny;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import binay.bemoapplication.R;
import binay.bemoapplication.api.PopularMoviesApi;
import binay.bemoapplication.generator.MovieApiGenerator;
import binay.bemoapplication.ui.model.MovieCreditsResponseModel;
import binay.bemoapplication.ui.model.MovieDetailModel;
import binay.bemoapplication.ui.model.MovieGenres;
import binay.bemoapplication.ui.model.MovieProductionCompanies;
import binay.bemoapplication.ui.model.MovieProductionCountries;
import binay.bemoapplication.ui.model.MovieResultsModel;
import binay.bemoapplication.ui.model.MovieReviewModel;
import binay.bemoapplication.ui.model.MovieReviewResults;
import binay.bemoapplication.ui.model.MovieVideoResponseModel;
import binay.bemoapplication.ui.model.MovieVideoResultModel;
import binay.bemoapplication.utils.Constants;
import binay.bemoapplication.utils.IntentUtils;
import binay.bemoapplication.utils.PaletteTransformation;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieDetailsFragment extends Fragment {


    String imageUrl, title, videoId1;
    MovieResultsModel model;
    boolean mFavourite = false;
    int circleColor;
    MovieDetailModel detailModel;
    PopularMoviesApi getPopularMovieApi;
    @BindView(R.id.itemImage)
    ImageView itemImage;
    @BindView(R.id.likeFab)
    FloatingActionButton likeFab;
    @BindView(R.id.ratingTitle)
    TextView ratingTitle;
    @BindView(R.id.runningTimeTextView)
    TextView runningTimeTextView;
    @BindView(R.id.releasedTextView)
    TextView releasedTextView;
    @BindView(R.id.topBarLayout)
    RelativeLayout topBarLayout;
    @BindView(R.id.plotOverviewTextView)
    TextView plotOverviewTextView;
    @BindView(R.id.trailerTitle)
    TextView trailerTitle;
    @BindView(R.id.movie_detail_trailer_container)
    LinearLayout movieDetailTrailerContainer;
    @BindView(R.id.scrollTrailer)
    HorizontalScrollView scrollTrailer;
    @BindView(R.id.trailerCount)
    TextView trailerCount;
    @BindView(R.id.trailerCard)
    CardView trailerCard;
    @BindView(R.id.tagText)
    TextView tagText;
    @BindView(R.id.taglineText)
    TextView taglineText;
    @BindView(R.id.tagLineDivider)
    View tagLineDivider;
    @BindView(R.id.imdbText)
    TextView imdbText;
    @BindView(R.id.productionCompaniesText)
    TextView productionCompaniesText;
    @BindView(R.id.productionCountriesText)
    TextView productionCountriesText;
    @BindView(R.id.reviewTitle)
    TextView reviewTitle;
    @BindView(R.id.movie_detail_review_container)
    LinearLayout movieDetailReviewContainer;
    @BindView(R.id.reviewCard)
    CardView reviewCard;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.movieSelectionText)
    TextView movieSelectionText;


    private String apiKey;
    private String imdb, genre, runningTime, released, productionCompanies, productionCountries, tagline, overview, rating;
    private ArrayList<MovieVideoResultModel> videoList;
    private ArrayList<MovieReviewResults> reviewsList;
    private MovieVideoResultModel mMainTrailer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
        handleArgs();
    }

    public static MovieDetailsFragment newInstance(Bundle bundle) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, rootView);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            nestedScrollView.setVisibility(View.VISIBLE);
            movieSelectionText.setVisibility(View.GONE);
            initHeader();
            videoList = new ArrayList<>();
            reviewsList = new ArrayList<>();
            apiKey = Constants.API_KEY;
            getPopularMovieApi = MovieApiGenerator.createService(PopularMoviesApi.class);
            getMovieDetails();
        }


        return rootView;
    }

    private void handleArgs() {

        if (getArguments() != null) {
            imageUrl = getArguments().getString("imageUrl");
            model = getArguments().getParcelable("movieObject");
        }
    }


    private void initHeader() {
        Picasso.with(getActivity()).load(imageUrl).transform(PaletteTransformation.instance()).into(itemImage,
                new PaletteTransformation.PaletteCallback(itemImage) {
                    @Override
                    protected void onSuccess(Palette palette) {
                        Palette.Swatch vibrant = palette.getVibrantSwatch();
                        if (vibrant != null) {
                            topBarLayout.setBackgroundColor(vibrant.getRgb());
                        } else {
                        }
                        Palette.Swatch vibrantDark = palette.getDarkVibrantSwatch();
                        if (vibrantDark != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                Window window = getActivity().getWindow();
                                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                window.setStatusBarColor(vibrantDark.getRgb());
                            }
                            if (vibrant == null) {
                                topBarLayout.setBackgroundColor(vibrantDark.getRgb());
                            }
                            //collapsingToolbar.setContentScrim(new ColorDrawable(vibrantDark.getRgb()));
                        }

                        Palette.Swatch mutedD = palette.getDarkMutedSwatch();
                        if (mutedD != null) {
                            likeFab.setBackgroundTintList(ColorStateList.valueOf(mutedD.getRgb()));
                        }
                        Palette.Swatch muted = palette.getLightMutedSwatch();
                        if (muted != null) {
                            circleColor = muted.getRgb();
                        }
                    }

                    @Override
                    public void onError() {
                    }
                });
        //  setupToolBar();
        plotOverviewTextView.setText(model.getOverview());
        ratingTitle.setText("Rating : " + model.getVote_average());



    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }




    private void getMovieDetails() {
        getPopularMovieApi.getMovieDetails(model.getmId(), apiKey, new Callback<MovieDetailModel>() {
            @Override
            public void success(MovieDetailModel movieDetailModel, Response response) {
                detailModel = movieDetailModel;
                try {
                    String imdbUrl = "IMDB : http://www.imdb.com/title/" + detailModel.getImdb_id();
                    imdbText.setText(imdbUrl);
                    Linkify.addLinks(imdbText, Linkify.WEB_URLS);
                    for (int i = 0; i < detailModel.getGenres().size(); i++) {
                        MovieGenres genres = detailModel.getGenres().get(i);
                        tagText.append(genres.getName() + "\n");
                    }
                    runningTimeTextView.setText("Runtime : " + detailModel.getRuntime() + " mins");
                    releasedTextView.setText("Released : " + detailModel.getRelease_date());
                    Spanny spanny;
                    for (MovieProductionCompanies companies : detailModel.getProduction_companies()) {
                        spanny = new Spanny(companies.getName() + "\n", new BulletSpan());
                        productionCompaniesText.append(spanny);
                    }
                    for (MovieProductionCountries countries : detailModel.getProduction_countries()) {
                        spanny = new Spanny(countries.getName() + "\n", new BulletSpan());
                        productionCountriesText.append(spanny);
                    }

                    if (!detailModel.getTagline().isEmpty())
                        taglineText.setText(detailModel.getTagline());
                    else {
                        tagLineDivider.setVisibility(View.GONE);
                        taglineText.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
        getMovieTrailersForId(model);
        getMovieCredits();

        getMovieReviews(model.getmId());
    }

    private void getMovieReviews(String id) {
        getPopularMovieApi.getMovieReviews(id, apiKey, new Callback<MovieReviewModel>() {
            @Override
            public void success(MovieReviewModel movieReviewModel, Response response) {
                if (movieReviewModel.getResults().size() != 0) {
                    reviewsList.addAll(movieReviewModel.getResults());
                    addReviewViews(reviewsList);
                } else {
                    reviewCard.setVisibility(View.GONE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }


    private void getMovieTrailersForId(MovieResultsModel model) {

        getPopularMovieApi.getVideoForId(model.getmId(), apiKey, new Callback<MovieVideoResponseModel>() {
            @Override
            public void success(MovieVideoResponseModel movieResponseModel, Response response) {

                if (movieResponseModel.getResults().size() != 0) {
                    videoList.addAll(movieResponseModel.getResults());
                    addTrailerViews(videoList);
                } else {
                    trailerCard.setVisibility(View.GONE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }


    private void addTrailerViews(List<MovieVideoResultModel> resultList) {

        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        trailerCount.setText(resultList.size() + " trailers");

        if (resultList != null && !resultList.isEmpty()) {
            mMainTrailer = resultList.get(0);
            itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.openYoutubeVideo(getActivity(), mMainTrailer.getKey());
                }
            });
            for (MovieVideoResultModel trailer : resultList) {
                final String key = trailer.getKey();
                final View trailerView = inflater.inflate(R.layout.list_item_trailer, movieDetailTrailerContainer, false);
                ImageView trailerImage = ButterKnife.findById(trailerView, R.id.trailer_poster_image_view);
                ImageView playImage = ButterKnife.findById(trailerView, R.id.play_trailer_image_view);
                playImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentUtils.openYoutubeVideo(getActivity(), key);
                    }
                });
                String url = "http://img.youtube.com/vi/" + key + "/0.jpg";
                Picasso.with(getActivity()).load(url).placeholder(R.drawable.ic_placeholder_movie)
                        .error(R.drawable.ic_placeholder_movie).into(trailerImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });


                movieDetailTrailerContainer.addView(trailerView);
            }
        }

    }


    private void addReviewViews(List<MovieReviewResults> resultList) {

        final LayoutInflater inflater = LayoutInflater.from(getActivity());

        for (MovieReviewResults review : resultList) {
            final View reviewView = inflater.inflate(R.layout.list_item_review, movieDetailReviewContainer, false);
            TextView reviewAuthor = ButterKnife.findById(reviewView, R.id.list_item_review_author_text_view);
            TextView reviewContent = ButterKnife.findById(reviewView, R.id.list_item_review_content_text_view);
            reviewAuthor.setText(review.getAuthor());
            reviewContent.setText(review.getContent());
            movieDetailReviewContainer.addView(reviewView);
        }
    }


    private void getMovieCredits() {
        getPopularMovieApi.getMovieCredits(model.getmId(), apiKey, new Callback<MovieCreditsResponseModel>() {
            @Override
            public void success(MovieCreditsResponseModel movieCreditsResponseModel, Response response) {
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }


}

