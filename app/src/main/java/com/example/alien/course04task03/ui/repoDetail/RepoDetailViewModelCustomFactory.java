package com.example.alien.course04task03.ui.repoDetail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.alien.course04task03.data.model.User;
import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;

import javax.inject.Named;

import io.reactivex.Single;

public class RepoDetailViewModelCustomFactory implements ViewModelProvider.Factory {
    private IGitHubRepository mRemoteRepository;
    private IGitHubRepository mLocalRepository;
    private Long mFilmId;
    private Single<User> mUser;

    public RepoDetailViewModelCustomFactory(IGitHubRepository remoteRepository,
                                            IGitHubRepository localRepository,
                                            Long filmId,
                                            Single<User> user) {
        this.mRemoteRepository = remoteRepository;
        this.mLocalRepository = localRepository;
        this.mFilmId = filmId;
        this.mUser = user;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RepoDetailViewModel(mRemoteRepository, mLocalRepository, mFilmId, mUser);

    }
}
