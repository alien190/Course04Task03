package com.example.alien.course04task03.di;

import com.example.alien.course04task03.BuildConfig;
import com.example.alien.course04task03.api.GitHubApi;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import toothpick.config.Module;


public class NetworkModule extends Module {

    private final OkHttpClient mOkHttpClient = provideClient();
    private final Gson mGson = provideGson();
    private final Retrofit mRetrofit = provideRetrofit();

    public NetworkModule() {
        bind(OkHttpClient.class).toInstance(mOkHttpClient);
        bind(Gson.class).toInstance(mGson);
        bind(Retrofit.class).toInstance(mRetrofit);
        bind(GitHubApi.class).toProviderInstance(this::provideApiService);
    }

    OkHttpClient provideClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        if (!BuildConfig.BUILD_TYPE.contains("release")) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }


    Gson provideGson() {
        return new Gson();
    }


    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                // need for interceptors
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    GitHubApi provideApiService() {
        return mRetrofit.create(GitHubApi.class);
    }
}
