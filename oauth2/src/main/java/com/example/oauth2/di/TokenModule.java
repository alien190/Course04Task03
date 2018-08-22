package com.example.oauth2.di;

import android.support.v4.app.Fragment;

import com.example.oauth2.BuildConfig;
import com.example.oauth2.api.HeaderInterceptor;
import com.example.oauth2.api.IAuthApi;
import com.example.oauth2.repository.gitHubRepository.IGHRepository;
import com.example.oauth2.repository.tokenValidator.ITokenValidator;
import com.example.oauth2.token.CustomWebViewClient;
import com.example.oauth2.token.ITokenViewModel;
import com.example.oauth2.token.TokenViewModelFactory;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import toothpick.config.Module;

public class TokenModule extends Module {
    private Fragment mFragment;

    private final HeaderInterceptor mHeaderInterceptor = new HeaderInterceptor();
    private final OkHttpClient mOkHttpClient = provideClient();
    private final Gson mGson = provideGson();
    private final Retrofit mAuthRetrofit = provideRetrofit(BuildConfig.BASE_URL);


    public TokenModule(Fragment fragment) {
        this.mFragment = fragment;
        bind(Fragment.class).toInstance(mFragment);
        bind(ITokenViewModel.class).toProvider(TokenViewModelProvider.class).providesSingletonInScope();
        bind(TokenViewModelFactory.class).toProvider(TokenViewModelFactoryProvider.class).providesSingletonInScope();
        bind(CustomWebViewClient.class).toInstance(new CustomWebViewClient());
        bind(IAuthApi.class).toProviderInstance(this::provideAuthApiService).providesSingletonInScope();
        bind(ITokenValidator.class).toProvider(TokenValidatorProvider.class).providesSingletonInScope();
        bind(IGHRepository.class).toProvider(GHRepositoryProvider.class).providesSingletonInScope();
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

    IAuthApi provideAuthApiService() {
        return mAuthRetrofit.create(IAuthApi.class);
    }

}
