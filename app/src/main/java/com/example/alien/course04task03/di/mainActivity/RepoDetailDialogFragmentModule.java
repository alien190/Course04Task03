package com.example.alien.course04task03.di.mainActivity;


import android.support.v4.app.DialogFragment;


import com.example.alien.course04task03.ui.repoDetail.RepoDetailViewModel;
import com.example.alien.course04task03.ui.repoDetail.RepoDetailViewModelCustomFactory;

import toothpick.config.Module;

public class RepoDetailDialogFragmentModule extends Module {

    private DialogFragment mFragment;
    private Long mFilmId;

    public RepoDetailDialogFragmentModule(DialogFragment fragment, long filmId) {
        this.mFragment = fragment;
        this.mFilmId = filmId;

        bind(DialogFragment.class).toInstance(mFragment);
        bind(RepoDetailViewModel.class).toProvider(RepoDetailViewModelProvider.class);
        bind(Long.class).withName("FilmId").toInstance(mFilmId);
        bind(RepoDetailViewModelCustomFactory.class).toProvider(RepoDetailViewModelCustomFactoryProvider.class);
    }
}
