package com.example.alien.course04task03.di.mainActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.DialogFragment;

import com.example.alien.course04task03.ui.repoDetail.RepoDetailViewModel;
import com.example.alien.course04task03.ui.repoDetail.RepoDetailViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

class RepoDetailViewModelProvider implements Provider<RepoDetailViewModel> {
    @Inject
    protected DialogFragment mFragment;
    @Inject
    protected RepoDetailViewModelCustomFactory mFactory;
    @Inject
    @Named("FilmId")
    Long mFilmId;


    @Override
    public RepoDetailViewModel get() {
        return ViewModelProviders.of(mFragment, mFactory).get(String.valueOf(mFilmId),RepoDetailViewModel.class);
    }
}