package com.example.alien.course04task03.di.mainActivity;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.repoDetail.RepoDetailViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class RepoDetailViewModelCustomFactoryProvider implements Provider<RepoDetailViewModelCustomFactory> {

    private IGitHubRepository mRemoteRepository;
    private IGitHubRepository mLocalRepository;
    private Long mFilmId;

    @Inject
    public RepoDetailViewModelCustomFactoryProvider(@Named("REMOTE") IGitHubRepository remoteRepository,
                                                    @Named("LOCAL") IGitHubRepository localRepository,
                                                    @Named("FilmId") Long filmId) {
        this.mRemoteRepository = remoteRepository;
        this.mLocalRepository = localRepository;
        this.mFilmId = filmId;
    }

    @Override
    public RepoDetailViewModelCustomFactory get() {
        return new RepoDetailViewModelCustomFactory(mRemoteRepository, mLocalRepository, mFilmId);
    }
}
