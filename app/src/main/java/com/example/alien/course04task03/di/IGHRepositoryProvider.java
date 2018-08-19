package com.example.alien.course04task03.di;

import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.repository.gitHubRepository.GHRepositoryImpl;
import com.example.alien.course04task03.repository.gitHubRepository.IGHRepository;

import javax.inject.Inject;
import javax.inject.Provider;

class IGHRepositoryProvider implements Provider<IGHRepository> {

    private IGitHubApi mIGitHubApi;

    @Inject
    public IGHRepositoryProvider(IGitHubApi IGitHubApi) {
        mIGitHubApi = IGitHubApi;
    }

    @Override
    public IGHRepository get() {
        return new GHRepositoryImpl(mIGitHubApi);
    }
}
