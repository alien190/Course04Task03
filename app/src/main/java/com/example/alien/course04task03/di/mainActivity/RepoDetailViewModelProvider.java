package com.example.alien.course04task03.di.mainActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.DialogFragment;

import com.example.alien.course04task03.ui.filmDetail.FilmDetailViewModel;
import com.example.alien.course04task03.ui.filmDetail.FilmDetailViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

class RepoDetailViewModelProvider implements Provider<FilmDetailViewModel> {
    @Inject
    protected DialogFragment mFragment;
    @Inject
    protected FilmDetailViewModelCustomFactory mFactory;
    @Inject
    @Named("FilmId")
    Long mFilmId;


    @Override
    public FilmDetailViewModel get() {
        return ViewModelProviders.of(mFragment, mFactory).get(String.valueOf(mFilmId),FilmDetailViewModel.class);
    }
}