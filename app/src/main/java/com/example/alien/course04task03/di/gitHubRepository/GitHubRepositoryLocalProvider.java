package com.example.alien.course04task03.di.gitHubRepository;

import com.example.alien.course04task03.data.IGitHubDao;
import com.example.alien.course04task03.repository.gitHubRepository.GitHubRepositoryLocalImpl;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;

import javax.inject.Inject;
import javax.inject.Provider;

class GitHubRepositoryLocalProvider implements Provider<IGitHubRepository> {

    private IGitHubDao mIGitHubDao;

    @Inject
    public GitHubRepositoryLocalProvider(IGitHubDao gitHubDao) {
        this.mIGitHubDao = gitHubDao;
    }

    @Override
    public IGitHubRepository get() {
        return new GitHubRepositoryLocalImpl(mIGitHubDao);
    }
}
