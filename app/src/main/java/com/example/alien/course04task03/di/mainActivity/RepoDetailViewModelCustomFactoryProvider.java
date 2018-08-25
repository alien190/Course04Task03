package com.example.alien.course04task03.di.mainActivity;

import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;
import com.example.alien.course04task03.ui.repoDetail.RepoDetailViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import io.reactivex.Single;

public class RepoDetailViewModelCustomFactoryProvider implements Provider<RepoDetailViewModelCustomFactory> {

    private IGitHubRepository mRemoteRepository;
    private IGitHubRepository mLocalRepository;
    private Long mFilmId;
    private Single<User> mUser;

    @Inject
    public RepoDetailViewModelCustomFactoryProvider(@Named("REMOTE") IGitHubRepository remoteRepository,
                                                    @Named("LOCAL") IGitHubRepository localRepository,
                                                    @Named("FilmId") Long filmId,
                                                    @Named("USER") Single<User> user) {
        this.mRemoteRepository = remoteRepository;
        this.mLocalRepository = localRepository;
        this.mFilmId = filmId;
        this.mUser = user;
    }

    @Override
    public RepoDetailViewModelCustomFactory get() {
        return new RepoDetailViewModelCustomFactory(mRemoteRepository, mLocalRepository, mFilmId, mUser);
    }
}
