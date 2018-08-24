package com.example.alien.course04task03.ui.repoDetail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;

import javax.inject.Named;

public class RepoDetailViewModelCustomFactory implements ViewModelProvider.Factory {
    private IGitHubRepository mRemoteRepository;
    private IGitHubRepository mLocalRepository;
    private Long mFilmId;

    public RepoDetailViewModelCustomFactory(IGitHubRepository remoteRepository,
                                            IGitHubRepository localRepository,
                                            Long filmId) {
        this.mRemoteRepository = remoteRepository;
        this.mLocalRepository = localRepository;
        this.mFilmId = filmId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RepoDetailViewModel(mRemoteRepository, mLocalRepository, mFilmId);

    }
}
