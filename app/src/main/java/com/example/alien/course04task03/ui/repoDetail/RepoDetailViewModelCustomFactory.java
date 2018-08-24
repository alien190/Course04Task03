package com.example.alien.course04task03.ui.repoDetail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.alien.course04task03.repository.gitHubRepository.IGitHubRepository;

public class RepoDetailViewModelCustomFactory implements ViewModelProvider.Factory {
    private IGitHubRepository mRepository;
    private Long mFilmId;

    public RepoDetailViewModelCustomFactory(IGitHubRepository repository, Long filmId) {
        this.mRepository = repository;
        this.mFilmId = filmId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RepoDetailViewModel(mRepository, mFilmId);

    }
}
