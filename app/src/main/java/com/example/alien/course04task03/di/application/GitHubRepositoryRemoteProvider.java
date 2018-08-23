package com.example.alien.course04task03.di.application;

import com.example.alien.course04task03.api.IGitHubApi;
import com.example.alien.course04task03.repository.gitHubRepository.GitHubRepositoryRemoteImpl;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;

import javax.inject.Inject;
import javax.inject.Provider;

class GitHubRepositoryRemoteProvider implements Provider<IGitHubRepository> {

    private IGitHubApi mIGitHubApi;

    @Inject
    public GitHubRepositoryRemoteProvider(IGitHubApi iGitHubApi) {
        this.mIGitHubApi = iGitHubApi;
    }

    @Override
    public IGitHubRepository get() {
        return new GitHubRepositoryRemoteImpl(mIGitHubApi);
    }
}
