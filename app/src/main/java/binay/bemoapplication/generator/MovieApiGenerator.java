package binay.bemoapplication.generator;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import binay.bemoapplication.BemoApplication;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieApiGenerator {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    BemoApplication application;

    // No need to instantiate this class.
    private MovieApiGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(BASE_URL).setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        Log.i("Nano", msg);
                    }
                })
                .setClient(new OkClient(new OkHttpClient()));

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }
}
