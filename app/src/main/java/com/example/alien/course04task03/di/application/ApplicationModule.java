package com.example.alien.course04task03.di.application;

import android.content.Context;

import com.example.alien.course04task03.BuildConfig;
import com.example.alien.course04task03.api.HeaderInterceptor;
import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.di.mainActivity.ViewModelCustomFactoryProvider;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.repository.sharedPref.ISharedPref;
import com.example.alien.course04task03.ui.common.ViewModelCustomFactory;
import com.example.oauth2.repository.tokenStorage.ITokenStorage;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import toothpick.config.Module;


public class ApplicationModule extends Module {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;

        bind(Context.class).toInstance(mContext);
        bind(ISharedPref.class).toProvider(SharedPrefProvider.class).providesSingletonInScope();
        bind(ITokenStorage.class).toProvider(SharedPrefProvider.class).providesSingletonInScope();
        bind(ViewModelCustomFactory.class).toProvider(ViewModelCustomFactoryProvider.class).providesSingletonInScope();
    }

}
