package com.example.alien.course04task03.di.mainActivity;


import android.support.v4.app.DialogFragment;


import com.example.alien.course04task03.ui.filmDetail.FilmDetailViewModel;
import com.example.alien.course04task03.ui.filmDetail.FilmDetailViewModelCustomFactory;

import toothpick.config.Module;

public class RepoDetailDialogFragmentModule extends Module {

    private DialogFragment mFragment;
    private Long mFilmId;

    public RepoDetailDialogFragmentModule(DialogFragment fragment, long filmId) {
        this.mFragment = fragment;
        this.mFilmId = filmId;

        bind(DialogFragment.class).toInstance(mFragment);
        bind(FilmDetailViewModel.class).toProvider(RepoDetailViewModelProvider.class);
        bind(Long.class).withName("FilmId").toInstance(mFilmId);
        bind(FilmDetailViewModelCustomFactory.class).toProvider(RepoDetailViewModelCustomFactoryProvider.class);
    }
}
