package com.example.alien.course04task03.di.gitHubRepository;

import android.content.Context;

import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;

import toothpick.config.Module;

public class GitHubRepositoryModule extends Module {


    private final Context mContext;


    public GitHubRepositoryModule(Context context) {
        mContext = context;

        bind(Context.class).toInstance(mContext);
        bind(IGitHubApi.class).toProvider(GitHubApiProvider.class).providesSingletonInScope();
        bind(IGitHubRepository.class).withName("REMOTE").toProvider(GitHubRepositoryRemoteProvider.class).providesSingletonInScope();
        bind(IGitHubRepository.class).withName("LOCAL").toProvider(GitHubRepositoryLocalProvider.class).providesSingletonInScope();
    }


}
