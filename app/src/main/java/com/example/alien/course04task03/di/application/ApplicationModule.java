package com.example.alien.course04task03.di.application;

import android.content.Context;

import com.example.alien.course04task03.BuildConfig;
import com.example.alien.course04task03.api.HeaderInterceptor;
import com.example.alien.course04task03.api.IAuthApi;
import com.example.alien.course04task03.api.IAuthInterceptor;
import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;
import com.example.alien.course04task03.repository.sharedPref.ISharedPref;
import com.example.oauth2.repository.tokenStorage.ITokenStorage;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import toothpick.config.Module;


public class ApplicationModule extends Module {

    private final HeaderInterceptor mHeaderInterceptor = new HeaderInterceptor();
    private final OkHttpClient mOkHttpClient = provideClient();
    private final Gson mGson = provideGson();
    private final Retrofit mGitHubRetrofit = provideRetrofit(BuildConfig.API_URL);
    private final Retrofit mAuthRetrofit = provideRetrofit(BuildConfig.AUTH_URL);
    private final Context mContext;


    public ApplicationModule(Context context) {
        mContext = context;

        bind(Context.class).toInstance(mContext);
//        bind(OkHttpClient.class).toInstance(mOkHttpClient);
//        bind(Gson.class).toInstance(mGson);
//        bind(Retrofit.class).toInstance(mGitHubRetrofit);
        bind(IGitHubApi.class).toProviderInstance(this::provideGitHubApiService).providesSingletonInScope();
        bind(IAuthApi.class).toProviderInstance(this::provideAuthApiService).providesSingletonInScope();
        bind(IGHRepository.class).toProvider(GHRepositoryProvider.class).providesSingletonInScope();
        bind(ISharedPref.class).toProvider(SharedPrefProvider.class).providesSingletonInScope();
        bind(ITokenStorage.class).toProvider(SharedPrefProvider.class).providesSingletonInScope();
        bind(IAuthInterceptor.class).toInstance(mHeaderInterceptor);
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


    IGitHubApi provideGitHubApiService() {
        return mGitHubRetrofit.create(IGitHubApi.class);
    }

    IAuthApi provideAuthApiService() {
        return mAuthRetrofit.create(IAuthApi.class);
    }
}
