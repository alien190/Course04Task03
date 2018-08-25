package com.example.alien.course04task03.di.gitHubRepository;

import android.content.Context;

import com.example.alien.course04task03.api.HeaderInterceptor;
import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.data.IGitHubDao;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;

import io.reactivex.Single;
import toothpick.config.Module;

public class GitHubRepositoryModule extends Module {


    private final Context mContext;


    public GitHubRepositoryModule(Context context) {
        mContext = context;

        bind(Context.class).toInstance(mContext);
        bind(HeaderInterceptor.class).toProvider(HeaderInterceptorProvider.class).providesSingletonInScope();
        bind(IGitHubApi.class).toProvider(GitHubApiProvider.class).providesSingletonInScope();
        bind(IGitHubDao.class).toProvider(GitHubDaoProvider.class).providesSingletonInScope();
        bind(IGitHubRepository.class).withName("REMOTE").toProvider(GitHubRepositoryRemoteProvider.class).providesSingletonInScope();
        bind(IGitHubRepository.class).withName("LOCAL").toProvider(GitHubRepositoryLocalProvider.class).providesSingletonInScope();
        bind(Single.class).withName("USER").toProvider(UserProvider.class).providesSingletonInScope();
    }

}
