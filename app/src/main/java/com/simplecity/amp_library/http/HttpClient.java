package com.simplecity.amp_library.http;

import com.simplecity.amp_library.http.lastfm.LastFmService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static class Holder {
        private static final HttpClient INSTANCE = new HttpClient();
    }

    public static HttpClient getInstance() {
        return Holder.INSTANCE;
    }

    public final OkHttpClient okHttpClient;
    public final LastFmService lastFmService;

    private static final String URL_LAST_FM = "https://ws.audioscrobbler.com/2.0/";

    private HttpClient() {
        okHttpClient = new OkHttpClient.Builder()
                .build();

        Retrofit lastFmRestAdapter = new Retrofit.Builder()
                .baseUrl(URL_LAST_FM)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        lastFmService = lastFmRestAdapter.create(LastFmService.class);
    }
}
