package com.example.alien.course04task03.di.mainActivity;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.repoDetail.RepoDetailViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class RepoDetailViewModelCustomFactoryProvider implements Provider<RepoDetailViewModelCustomFactory> {

    protected IGitHubRepository mRepository;
    private Long mFilmId;

    @Inject
    public RepoDetailViewModelCustomFactoryProvider(IGitHubRepository mRepository, @Named("FilmId") Long filmId) {
        this.mRepository = mRepository;
        this.mFilmId = filmId;
    }

    @Override
    public RepoDetailViewModelCustomFactory get() {
        return new RepoDetailViewModelCustomFactory(mRepository, mFilmId);
    }
}
