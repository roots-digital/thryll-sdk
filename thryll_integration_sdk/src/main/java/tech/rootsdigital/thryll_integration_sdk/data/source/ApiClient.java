package tech.rootsdigital.thryll_integration_sdk.data.source;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://thryll.app/api/";
//    private static final String BASE_URL = "http://18.219.98.161/api/";
    private static Retrofit retrofit = null;


    public static ApiService getAPIService() {
        return (ApiService) retrofit.create(ApiService.class);
    }

    public static void Initialize(Application application){

        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);

        File httpCacheDirectory = new File(application.getCacheDir(), "offlineCache");
        //10 MB
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        final int app_key = application.getResources().getIdentifier("thryll_app_key",
                "string", application.getPackageName());

        Interceptor credentials = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("app", application.getPackageName())
                        .header("Accept", "application/vnd.yourapi.v1.full+json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(logger)
                .addInterceptor(credentials)
                .addInterceptor(provideCacheInterceptor(application.getApplicationContext()))
//                .addInterceptor(provideOfflineCacheInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


    private static Interceptor provideCacheInterceptor(Context context) {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (hasNetwork(context))
                    request = request.newBuilder().header("Cache-Control", "public, max-age=" + 10).build();
                else
                    request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();

                return chain.proceed(request);
            }
        };

    }

    private static boolean hasNetwork(Context context){
        boolean isConnected = false; // Initial Value
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected())
            isConnected = true;

        return isConnected;
    }

    private static Interceptor provideOfflineCacheInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                try {
                    return chain.proceed(chain.request());
                } catch (Exception e) {


                    CacheControl cacheControl = new CacheControl.Builder()
//                            .onlyIfCached()
                            .maxStale(1, TimeUnit.DAYS)
                            .build();

                    Request offlineRequest = chain.request().newBuilder()
                            .cacheControl(cacheControl)
                            .build();

                    return chain.proceed(offlineRequest);
                }
            }
        };
    }
}
