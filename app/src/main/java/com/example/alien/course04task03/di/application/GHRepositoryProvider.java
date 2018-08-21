package com.example.alien.course04task03.di.application;

import com.example.alien.course04task03.api.IAuthApi;
import com.example.alien.course04task03.api.IAuthInterceptor;
import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.repository.gitHubRepository.GHRepositoryImpl;
import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;

import javax.inject.Inject;
import javax.inject.Provider;

class GHRepositoryProvider implements Provider<IGHRepository> {

    private IGitHubApi mIGitHubApi;
    private IAuthApi mIAuthApi;
    private IAuthInterceptor mIAuthInterceptor;

    @Inject
    public GHRepositoryProvider(IGitHubApi iGitHubApi, IAuthApi iAuthApi, IAuthInterceptor iAuthInterceptor) {
        this.mIGitHubApi = iGitHubApi;
        this.mIAuthApi = iAuthApi;
        this.mIAuthInterceptor = iAuthInterceptor;
    }

    @Override
    public IGHRepository get() {
        return new GHRepositoryImpl(mIGitHubApi, mIAuthApi, mIAuthInterceptor);
    }
}
