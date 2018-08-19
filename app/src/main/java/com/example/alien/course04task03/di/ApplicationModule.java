package com.example.alien.course04task03.di;

import android.content.Context;

import com.example.alien.course04task03.BuildConfig;
import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;
import com.example.alien.course04task03.repository.sharedPref.ISharedPref;
import com.example.alien.course04task03.repository.tokenValidator.ITokenValidator;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import toothpick.config.Module;


public class ApplicationModule extends Module {

    private final OkHttpClient mOkHttpClient = provideClient();
    private final Gson mGson = provideGson();
    private final Retrofit mRetrofit = provideRetrofit();
    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;

        bind(Context.class).toInstance(mContext);
        bind(OkHttpClient.class).toInstance(mOkHttpClient);
        bind(Gson.class).toInstance(mGson);
        bind(Retrofit.class).toInstance(mRetrofit);
        bind(IGitHubApi.class).toProviderInstance(this::provideApiService).providesSingletonInScope();
        bind(ITokenValidator.class).toProvider(ITokenValidatorProvider.class).providesSingletonInScope();
        bind(IGHRepository.class).toProvider(IGHRepositoryProvider.class).providesSingletonInScope();
        bind(ISharedPref.class).toProvider(ISharedPrefProvider.class).providesSingletonInScope();
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


    IGitHubApi provideApiService() {
        return mRetrofit.create(IGitHubApi.class);
    }
}
