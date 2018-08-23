package com.example.alien.course04task03.di.gitHubRepository;

import com.example.alien.course04task03.BuildConfig;
import com.example.alien.course04task03.api.HeaderInterceptor;
import com.example.alien.course04task03.api.IGitHubApi;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Provider;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubApiProvider implements Provider<IGitHubApi> {
    private final Gson mGson = provideGson();
    private HeaderInterceptor mHeaderInterceptor;
    private OkHttpClient mOkHttpClient;
    private Retrofit mGitHubRetrofit;

    @Inject
    public GitHubApiProvider(HeaderInterceptor headerInterceptor) {
        this.mHeaderInterceptor = headerInterceptor;
        this.mOkHttpClient = provideClient();
        this.mGitHubRetrofit = provideRetrofit(BuildConfig.API_URL);
    }

    @Override
    public IGitHubApi get() {
        return mGitHubRetrofit.create(IGitHubApi.class);
    }

    OkHttpClient provideClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(mHeaderInterceptor);

        if (!BuildConfig.BUILD_TYPE.contains("release")) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }


    Gson provideGson() {
        return new Gson();
    }

    Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                // need for interceptors
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
