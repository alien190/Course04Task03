package com.example.alien.course04task03.di.application;

import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.repository.gitHubRepository.GHRepositoryImpl;
import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;

import javax.inject.Inject;
import javax.inject.Provider;

class GHRepositoryProvider implements Provider<IGHRepository> {

    private IGitHubApi mIGitHubApi;

    @Inject
    public GHRepositoryProvider(IGitHubApi iGitHubApi) {
        this.mIGitHubApi = iGitHubApi;
    }

    @Override
    public IGHRepository get() {
        return new GHRepositoryImpl(mIGitHubApi);
    }
}
