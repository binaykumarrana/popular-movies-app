package binay.bemoapplication.api;

import binay.bemoapplication.ui.model.MovieCreditsResponseModel;
import binay.bemoapplication.ui.model.MovieDetailModel;
import binay.bemoapplication.ui.model.MovieResponseModel;
import binay.bemoapplication.ui.model.MovieReviewModel;
import binay.bemoapplication.ui.model.MovieVideoResponseModel;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Binay on 15/06/17.
 */

public interface PopularMoviesApi {
    @GET("/discover/movie?sort_by=popularity.desc")
    void getPopularMovies(@Query("page") int page, @Query("api_key") String api_key, Callback<MovieResponseModel> movieResponseModelCallback);

    @GET("/movie/{id}")
    void getMovieDetails(@Path("id") String movieId, @Query("api_key") String api_key, Callback<MovieDetailModel> cb);

    @GET("/movie/{id}/reviews")
    void getMovieReviews(@Path("id") String movieId, @Query("api_key") String api_key, Callback<MovieReviewModel> cb);

    @GET("/movie/{id}/videos")
    void getVideoForId(@Path("id") String movieId, @Query("api_key") String api_key, Callback<MovieVideoResponseModel> cb);
    @GET("/movie/{id}/credits")
    void getMovieCredits(@Path("id") String movieId, @Query("api_key") String api_key, Callback<MovieCreditsResponseModel> cb);

}
